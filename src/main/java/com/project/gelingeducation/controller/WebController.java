package com.project.gelingeducation.controller;

import com.project.gelingeducation.common.annotation.Limit;
import com.project.gelingeducation.common.annotation.Log;
import com.project.gelingeducation.common.controller.BaseController;
import com.project.gelingeducation.common.dto.JsonData;
import com.project.gelingeducation.common.dto.WebIndex;
import com.project.gelingeducation.common.server.ValidateCodeService;
import com.project.gelingeducation.entity.LoginLog;
import com.project.gelingeducation.entity.User;
import com.project.gelingeducation.entity.WebDataBean;
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
 * @author LL
 * @Valid 用于验证bean是否符合注解要求
 */
@RestController
public class WebController extends BaseController {

    @Autowired
    private ILoginLogService loginLogService;
    @Autowired
    private IWebDataBeanService webDataBeanService;
    @Autowired
    private IUserService userService;
    @Autowired
    private ValidateCodeService validateCodeService;

    @Log("web端首页")
    @RequestMapping(value = "/web/index")
    public Object index() {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        LoginLog loginLog = loginLogService.getByUserId(user.getId());
        WebDataBean webDataBean = webDataBeanService.getWebDataBean();
        WebIndex webIndex = new WebIndex();
        webIndex.setLastLoginTime(loginLog.getLastLoginTime());
        webIndex.setAllLoginMun(webDataBean.getAllLoginMun());
        webIndex.setTodayLoginIpMun(webDataBean.getTodayLoginIpMun());
        webIndex.setTodayLoginMun(webDataBean.getTodayLoginMun());
        return JsonData.buildSuccess(webIndex);
    }

    @Limit(key = "login", period = 60, count = 20, name = "登录接口", prefix = "limit")
    @Log("登录接口")
    @RequestMapping(value = "/web/login", method = RequestMethod.POST)
    public Object login(@RequestBody @Validated User user) {
        return JsonData.buildSuccess(webDataBeanService.login(user));
    }

    @Log("注册接口")
    @RequestMapping(value = "/web/register", method = RequestMethod.POST)
    public Object register(@RequestBody User user) {
        return JsonData.buildSuccess(userService.register(user));
    }

    @GetMapping("/web/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        validateCodeService.create(request, response);
    }
}
