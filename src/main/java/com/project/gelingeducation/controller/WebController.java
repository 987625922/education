package com.project.gelingeducation.controller;

import com.project.gelingeducation.common.annotation.Log;
import com.project.gelingeducation.common.utils.JWTUtil;
import com.project.gelingeducation.common.config.GLConstant;
import com.project.gelingeducation.common.dto.JsonData;
import com.project.gelingeducation.common.dto.WebIndex;
import com.project.gelingeducation.common.exception.AllException;
import com.project.gelingeducation.common.exception.StatusEnum;
import com.project.gelingeducation.common.utils.MD5Util;
import com.project.gelingeducation.common.utils.RedisTemplateUtil;
import com.project.gelingeducation.common.utils.TokenUtil;
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

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
public class WebController {

    @Autowired
    private ILoginLogService loginLogService;
    @Autowired
    private IWebDataBeanService webDataBeanService;
    @Autowired
    private IUserService UserService;
    @Autowired
    RedisTemplateUtil templateUtil;


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

    @RequestMapping(value = "/web/login", method = RequestMethod.POST)
    public Object login(@RequestBody User user, HttpServletRequest request) {

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
        String token = JWTUtil.sign(reUser.getAccount(), reUser.getPassword());
        HashMap userMap = new HashMap();
        userMap.put("id", reUser.getId());
        userMap.put("token", token);
        templateUtil.set(GLConstant.TOKEN_CACHE_PREFIX + TokenUtil.encryptToken(token)
                + "." + reUser.getAccount(), reUser.getId().toString());
        return JsonData.buildSuccess(userMap);
    }

    @Log("注册接口")
    @RequestMapping(value = "/web/register", method = RequestMethod.POST)
    public Object register(@RequestBody User user) {
        return JsonData.buildSuccess(UserService.register(user));
    }


}
