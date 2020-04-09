package com.project.gelingeducation.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.project.gelingeducation.common.dto.JsonData;
import com.project.gelingeducation.common.dto.RequestBodyData;
import com.project.gelingeducation.common.exception.AllException;
import com.project.gelingeducation.common.utils.CommonUtil;
import com.project.gelingeducation.common.utils.EncryptionUtils;
import com.project.gelingeducation.domain.User;
import com.project.gelingeducation.service.IUserService;
import com.project.gelingeducation.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * 不用登录就可以请求
 */
@RestController
public class PubliceController {

    @Autowired
    private IUserService UserService;
    @Autowired
    private LoginLogService loginLogService;

    /**
     * 登录接口
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object login(@RequestBody User user) {

        User reUser = UserService.login(user.getAccount(), user.getPassword());
        if (reUser == null)
            throw new AllException(-101, "账号密码错误");

        loginLogService.getByUserIdLoginUpdate(reUser.getId());

        HashMap userMap = new HashMap();
        userMap.put("id", reUser.getId());
        userMap.put("token", reUser.getAccount());
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

}
