package com.project.gelingeducation.controller;

import com.project.gelingeducation.common.annotation.Log;
import com.project.gelingeducation.common.dto.JsonData;
import com.project.gelingeducation.common.dto.WebIndex;
import com.project.gelingeducation.common.utils.RedisTemplateUtil;
import com.project.gelingeducation.domain.LoginLog;
import com.project.gelingeducation.domain.User;
import com.project.gelingeducation.domain.WebDataBean;
import com.project.gelingeducation.service.ILoginLogService;
import com.project.gelingeducation.service.IUserService;
import com.project.gelingeducation.service.IWebDataBeanService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    @Autowired
    private ILoginLogService loginLogService;
    @Autowired
    private IWebDataBeanService webDataBeanService;
    @Autowired
    private IUserService userService;

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

    @Log("登录接口")
    @RequestMapping(value = "/web/login", method = RequestMethod.POST)
    public Object login(@RequestBody User user) {
        return JsonData.buildSuccess(webDataBeanService.login(user));
    }

    @Log("注册接口")
    @RequestMapping(value = "/web/register", method = RequestMethod.POST)
    public Object register(@RequestBody User user) {
        return JsonData.buildSuccess(userService.register(user));
    }
}
