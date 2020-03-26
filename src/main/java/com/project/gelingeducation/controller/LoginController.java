package com.project.gelingeducation.controller;

import com.project.gelingeducation.domain.User;
import com.project.gelingeducation.domain.JsonData;
import com.project.gelingeducation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 登录接口
     *
     * @param user 用户账号密码
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object login(@RequestBody User user) {
        return JsonData.buildSuccess(userService.login(user));
    }

    /**
     * 注册接口
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Object register(@RequestBody User user) {
        return JsonData.buildSuccess(userService.register(user));
    }

    /**
     * 首页
     */
    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public Object register(long id) {

        return JsonData.buildSuccess();
    }


}
