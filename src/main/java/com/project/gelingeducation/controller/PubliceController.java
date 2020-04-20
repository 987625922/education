package com.project.gelingeducation.controller;

import com.project.gelingeducation.common.authentication.JWTUtil;
import com.project.gelingeducation.common.dto.JsonData;
import com.project.gelingeducation.common.exception.AllExceptionEnum;
import com.project.gelingeducation.common.utils.HttpContextUtil;
import com.project.gelingeducation.common.utils.IPUtil;
import com.project.gelingeducation.common.utils.MD5Util;
import com.project.gelingeducation.domain.User;
import com.project.gelingeducation.service.IUserService;
import com.project.gelingeducation.service.IWebDataBeanService;
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

    @Autowired
    private IWebDataBeanService webDataBeanService;

    /**
     * 登录接口
     *
     * @return
     */
    @RequestMapping(value = "/web/login", method = RequestMethod.POST)
    public Object login(@RequestBody User user) {

        User reUser = UserService.findUserByAccount(user.getAccount());

        if (reUser == null) {
            throw AllExceptionEnum.NO_USER.getAllException();
        } else if (!reUser.getPassword().equals(MD5Util.encrypt(user.getAccount().toLowerCase(),
                user.getPassword()))) {
            throw AllExceptionEnum.ACCOUNT_PASSWORD_ERROR.getAllException();
        } else if (reUser.getStatus() == 0) {
            throw AllExceptionEnum.BAN_USER.getAllException();
        }

        loginLogService.getByUserIdLoginUpdate(reUser.getId());

        webDataBeanService.userLogin();

        HashMap userMap = new HashMap();
        userMap.put("id", reUser.getId());
        userMap.put("token", JWTUtil.sign(reUser.getAccount(), reUser.getPassword()));
        return JsonData.buildSuccess(userMap);
    }

    /**
     * 注册接口
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/web/register", method = RequestMethod.POST)
    public Object register(@RequestBody User user) {
        return JsonData.buildSuccess(UserService.register(user));
    }


}
