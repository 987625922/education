package com.project.gelingeducation.controller;

import com.project.gelingeducation.common.authentication.JWTUtil;
import com.project.gelingeducation.common.dto.JsonData;
import com.project.gelingeducation.common.dto.WebIndex;
import com.project.gelingeducation.common.exception.AllException;
import com.project.gelingeducation.common.exception.StatusEnum;
import com.project.gelingeducation.common.utils.MD5Util;
import com.project.gelingeducation.domain.LoginLog;
import com.project.gelingeducation.domain.User;
import com.project.gelingeducation.domain.WebDataBean;
import com.project.gelingeducation.service.IUserService;
import com.project.gelingeducation.service.IWebDataBeanService;
import com.project.gelingeducation.service.ILoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class WebController {

    @Autowired
    private ILoginLogService loginLogService;
    @Autowired
    private IWebDataBeanService webDataBeanService;
    @Autowired
    private IUserService UserService;


    /**
     * web端首页
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/web/index", method = RequestMethod.GET)
    public Object index(long id) {
        LoginLog loginLog = loginLogService.getByUserId(id);
        WebDataBean webDataBean = webDataBeanService.getWebDataBean();
        WebIndex webIndex = new WebIndex();
        webIndex.setLastLoginTime(loginLog.getLastLoginTime());
        webIndex.setAllLoginMun(webDataBean.getAllLoginMun());
        webIndex.setTodayLoginIpMun(webDataBean.getTodayLoginIpMun());
        webIndex.setTodayLoginMun(webDataBean.getTodayLoginMun());
        return JsonData.buildSuccess(webIndex);
    }

    /**
     * 登录接口
     *
     * @return
     */
    @RequestMapping(value = "/web/login", method = RequestMethod.POST)
    public Object login(@RequestBody User user) {

        User reUser = UserService.findUserByAccount(user.getAccount());

        if (reUser == null) {
            throw new AllException(StatusEnum.NO_USER);
        } else if (!reUser.getPassword().equals(MD5Util.encrypt(user.getAccount().toLowerCase(),
                user.getPassword()))) {
            throw new AllException(StatusEnum.ACCOUNT_PASSWORD_ERROR);
        } else if (reUser.getStatus() == 0) {
            throw new AllException(StatusEnum.BAN_USER);
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
