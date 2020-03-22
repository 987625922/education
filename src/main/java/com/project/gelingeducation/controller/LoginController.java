package com.project.gelingeducation.controller;

import com.project.gelingeducation.domain.AdminInfo;
import com.project.gelingeducation.domain.JsonData;
import com.project.gelingeducation.service.AdminInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private AdminInfoService adminInfoService;

    /**
     * 登录接口
     *
     * @param adminInfo 用户账号密码
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object login(@RequestBody AdminInfo adminInfo) {
        return JsonData.buildSuccess(adminInfoService.login(adminInfo));
    }

    /**
     * 注册接口
     *
     * @param adminInfo
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Object register(@RequestBody AdminInfo adminInfo) {
        return JsonData.buildSuccess(adminInfoService.register(adminInfo));
    }

    /**
     * 首页
     */
    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public Object register(long id) {
        adminInfoService.updateLastLoginTime(id);

        return JsonData.buildSuccess();
    }


}
