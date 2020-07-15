package com.project.gelingeducation.service.impl;

import com.project.gelingeducation.common.config.GLConstant;
import com.project.gelingeducation.common.exception.AllException;
import com.project.gelingeducation.common.exception.StatusEnum;
import com.project.gelingeducation.common.server.ValidateCodeService;
import com.project.gelingeducation.common.utils.*;
import com.project.gelingeducation.entity.User;
import com.project.gelingeducation.service.ILoginLogService;
import com.project.gelingeducation.service.IUserService;
import com.project.gelingeducation.service.IWebDataBeanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

/**
 * @Author: LL
 * @Description: 视频的Service
 */
@Transactional(readOnly = true)
@Service
public class WebDataBeanServiceImpl implements IWebDataBeanService {

    /**
     * 用户实体类service
     */
    @Autowired
    private IUserService userService;

    /**
     * 登录日志实体类service
     */
    @Autowired
    private ILoginLogService loginLogService;
    /**
     * rides工具类
     */
    @Autowired
    RedisTemplateUtil templateUtil;

    /**
     * 用户登录
     *
     * @param account  账号
     * @param password 密码
     * @return
     */
    @Override
    public Object login(String account, String password) {
        //通过用户名获取用户
        User reUser = userService.findUserByAccount(account);
        if (reUser == null) {
            throw new AllException(StatusEnum.NO_USER);
        } else if (!reUser.getPassword().equals(MD5Util.encrypt(account.toLowerCase(), password))) {
            throw new AllException(StatusEnum.ACCOUNT_PASSWORD_ERROR);
        } else if (reUser.getStatus() == 0) {
            throw new AllException(StatusEnum.BAN_USER);
        }
        //更新登录log,可以开启一个格外的线程去处理
        ExecutorsUtils.getInstance().execute(() -> {
            loginLogService.saveOrUpdateLoginLogByUid(reUser);
            addLoginMun();
        });
        //返回uid和jwtToken
        String token = JWTUtil.sign(reUser.getAccount(), reUser.getPassword());
        HashMap userMap = new HashMap(2);
        userMap.put("id", reUser.getId());
        userMap.put("token", token);
        //设置redis token缓存和过期时间
        templateUtil.set(GLConstant.TOKEN_CACHE_PREFIX + TokenUtil.encryptToken(token)
                + "." + reUser.getAccount(), token, GLConstant.TOKEN_CACHE_TIME_SECONDS);
        return userMap;
    }


    /**
     * 添加登录数
     */
    @Override
    public void addLoginMun() {
        Integer integer = (Integer) templateUtil.get(GLConstant.ALL_LOGIN_NUM_KEY);
//        integer.compareTo()
    }

    /**
     * 清空今天的登录数
     */
    @Override
    public void clearTodayLoginMun() {

    }
}

