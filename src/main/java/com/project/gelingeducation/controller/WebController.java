package com.project.gelingeducation.controller;

import com.project.gelingeducation.common.annotation.Limit;
import com.project.gelingeducation.common.annotation.Log;
import com.project.gelingeducation.common.controller.BaseController;
import com.project.gelingeducation.common.dto.JsonResult;
import com.project.gelingeducation.common.dto.WebIndex;
import com.project.gelingeducation.common.server.ValidateCodeService;
import com.project.gelingeducation.entity.LoginLog;
import com.project.gelingeducation.entity.User;
import com.project.gelingeducation.service.ILoginLogService;
import com.project.gelingeducation.service.IUserService;
import com.project.gelingeducation.service.IWebDataBeanService;
import org.apache.shiro.SecurityUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * web特定的实体类返回
 *
 * @author LL
 * @Valid 用于验证bean是否符合注解要求
 */
@RestController
public class WebController extends BaseController {
    /**
     * 登录log的service
     */
    @Autowired
    private ILoginLogService loginLogService;
    /**
     * web特定的实体类返回service
     */
    @Autowired
    private IWebDataBeanService webDataBeanService;
    /**
     * 用户实体类的service
     */
    @Autowired
    private IUserService userService;
    /**
     * 登录验证码的service
     */
    @Autowired
    private ValidateCodeService validateCodeService;

    /**
     * web管理后台首页返回
     *
     * @return 首页信息
     */
    @Log("web端首页")
    @RequestMapping(value = "/web/index")
    public Object index() {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        LoginLog loginLog = loginLogService.getByUserId(user.getId());
//        WebDataBean webDataBean = webDataBeanService.getWebDataBean();
        WebIndex webIndex = new WebIndex();
        webIndex.setLastLoginTime(loginLog.getLastLoginTime());
//        webIndex.setAllLoginMun(webDataBean.getAllLoginMun());
//        webIndex.setTodayLoginIpMun(webDataBean.getTodayLoginIpMun());
//        webIndex.setTodayLoginMun(webDataBean.getTodayLoginMun());
        return JsonResult.buildSuccess(webIndex);
    }

    /**
     * 登录接口
     *
     * @param account    账号
     * @param password   密码
     * @param verifyCode 验证码
     * @param key        验证码在redis中的key
     * @return 用户实体
     */
    @Limit(key = "login", period = 60, count = 20, name = "登录接口", prefix = "limit")
    @Log("登录接口")
    @RequestMapping(value = "/web/login")
    public Object login(@NotBlank(message = "account") String account,
                        @NotBlank(message = "password") String password,
                        @NotBlank(message = "verifyCode") String verifyCode,
                        @NotBlank(message = "key") String key) {
        //验证验证码是否正确
        validateCodeService.check(key, verifyCode);
        return JsonResult.buildSuccess(webDataBeanService.login(account, password));
    }

    /**
     * 注册接口
     *
     * @param user 用户实体
     * @return 用户实体
     */
    @Log("注册接口")
    @RequestMapping(value = "/web/register", method = RequestMethod.POST)
    public Object register(@RequestBody @Validated User user) {
        return JsonResult.buildSuccess(userService.register(user));
    }

    /**
     * 登录二维码
     *
     * @return 返回验证码dto实体类
     */
    @GetMapping("/web/captcha")
    @Limit(key = "get_captcha", period = 60, count = 10, name = "获取验证码", prefix = "limit")
    public Object captcha() {
        return JsonResult.buildSuccess(validateCodeService.create());
    }
}
