package com.project.gelingeducation.controller;

import com.project.gelingeducation.common.annotation.Limit;
import com.project.gelingeducation.common.annotation.Log;
import com.project.gelingeducation.common.controller.BaseController;
import com.project.gelingeducation.common.dto.JsonData;
import com.project.gelingeducation.common.dto.WebIndex;
import com.project.gelingeducation.common.server.ValidateCodeService;
import com.project.gelingeducation.entity.LoginLog;
import com.project.gelingeducation.entity.User;
import com.project.gelingeducation.service.ILoginLogService;
import com.project.gelingeducation.service.IUserService;
import com.project.gelingeducation.service.IWebDataBeanService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        return JsonData.buildSuccess(webIndex);
    }

    /**
     * 登录信息
     *
     * @param user 用户实体
     * @return 用户实体
     */
    @Limit(key = "login", period = 60, count = 20, name = "登录接口", prefix = "limit")
    @Log("登录接口")
    @RequestMapping(value = "/web/login", method = RequestMethod.POST)
    public Object login(@RequestBody @Validated User user) {
        return JsonData.buildSuccess(webDataBeanService.login(user));
    }

    /**
     * 注册接口
     *
     * @param user 用户实体
     * @return 用户实体
     */
    @Log("注册接口")
    @RequestMapping(value = "/web/register", method = RequestMethod.POST)
    public Object register(@RequestBody User user) {
        return JsonData.buildSuccess(userService.register(user));
    }

    /**
     * 登录二维码
     *
     * @param request  /
     * @param response /
     * @throws Exception
     */
    @GetMapping("/web/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        validateCodeService.create(request, response);
    }
}
