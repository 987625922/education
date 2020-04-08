package com.project.gelingeducation.controller;

import com.project.gelingeducation.common.dto.JsonData;
import com.project.gelingeducation.common.exception.AllException;
import com.project.gelingeducation.common.utils.MD5Util;
import com.project.gelingeducation.domain.User;
import com.project.gelingeducation.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * 不用登录就可以请求
 */
//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class PubliceController {

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
//            UsernamePasswordToken usernamePasswordToken =
//                    new UsernamePasswordToken(user.getAccount(),
//                            MD5Util.encrypt(user.getAccount().toLowerCase(), user.getPassword()));
//            subject.login(usernamePasswordToken);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AllException(-100, "账号或密码错误!");
        }
        HashMap userMap = new HashMap();
        userMap.put("id", UserService.login(user.getAccount(), user.getPassword()).getId());
        userMap.put("token", subject.getSession().getId());
        return JsonData.buildSuccess(userMap);
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


    @RequestMapping(value = "/need_login", method = RequestMethod.GET)
    public Object needLogin() {
        return JsonData.buildError("需要登录账户");
    }

    @RequestMapping(value = "/not_permisson", method = RequestMethod.GET)
    public Object needPermisson() {
        return JsonData.buildError("账户没有权限");
    }

}
