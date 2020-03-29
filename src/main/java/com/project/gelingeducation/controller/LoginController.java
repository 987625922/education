package com.project.gelingeducation.controller;

import com.project.gelingeducation.domain.User;
import com.project.gelingeducation.common.dto.JsonData;
import com.project.gelingeducation.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private IUserService UserService;

    /**
     * 登录接口
     *
     * @param user 用户账号密码
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object login(@RequestBody User user) {
        Subject subject = SecurityUtils.getSubject();
        try {
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getAccount(), user.getPassword());
            subject.login(usernamePasswordToken);
            return JsonData.buildSuccess("登录成功");

        } catch (Exception e) {
            e.printStackTrace();

            return JsonData.buildError("账号或者密码错误");

        }

//        return JsonData.buildSuccess(UserService.login(user));
    }

    /**
     * 注册接口
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Object register(@RequestBody User user) {
        return JsonData.buildSuccess(UserService.register(user));
    }

    /**
     * 首页
     */
    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public Object register(long id) {

        return JsonData.buildSuccess();
    }


}
