package com.project.gelingeducation.service.Impl;

import com.project.gelingeducation.common.config.GLConstant;
import com.project.gelingeducation.common.exception.AllException;
import com.project.gelingeducation.common.exception.StatusEnum;
import com.project.gelingeducation.common.utils.JWTUtil;
import com.project.gelingeducation.common.utils.MD5Util;
import com.project.gelingeducation.common.utils.RedisTemplateUtil;
import com.project.gelingeducation.common.utils.TokenUtil;
import com.project.gelingeducation.dao.IWebDataBeanDao;
import com.project.gelingeducation.domain.User;
import com.project.gelingeducation.domain.WebDataBean;
import com.project.gelingeducation.service.ILoginLogService;
import com.project.gelingeducation.service.IUserService;
import com.project.gelingeducation.service.IWebDataBeanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Transactional(readOnly = true)
@Service
public class WebDataBeanServiceImpl implements IWebDataBeanService {

    @Autowired
    private IWebDataBeanDao webDataBeanDao;
    @Autowired
    private IUserService userService;
    @Autowired
    private ILoginLogService loginLogService;
    @Autowired
    RedisTemplateUtil templateUtil;

    @Override
    public WebDataBean findById(Integer id) {
        return webDataBeanDao.findById(id);
    }

    @Override
    @Transactional
    public void save(WebDataBean webDataBean) {
        webDataBeanDao.save(webDataBean);
    }

    @Override
    @Transactional
    public void update(WebDataBean webDataBean) {
        webDataBeanDao.update(webDataBean);
    }

    @Override
    @Transactional
    public Object login(User user) {
        //通过用户名获取用户
        User reUser = userService.findUserByAccount(user.getAccount());
        if (reUser == null) {
            throw new AllException(StatusEnum.NO_USER);
        } else if (!reUser.getPassword().equals(MD5Util.encrypt(user.getAccount().toLowerCase(),
                user.getPassword()))) {
            throw new AllException(StatusEnum.ACCOUNT_PASSWORD_ERROR);
        } else if (reUser.getStatus() == 0) {
            throw new AllException(StatusEnum.BAN_USER);
        }
        //更新登录log,可以开启一个格外的线程去处理，先把结果返回了
        loginLogService.saveOrUpdateLoginLogByUid(reUser);
        //返回uid和jwtToken
        String token = JWTUtil.sign(reUser.getAccount(), reUser.getPassword());
        HashMap userMap = new HashMap();
        userMap.put("id", reUser.getId());
        userMap.put("token", token);
        //设置redis token缓存和过期时间
        templateUtil.set(GLConstant.TOKEN_CACHE_PREFIX + TokenUtil.encryptToken(token)
                + "." + reUser.getAccount(), token, GLConstant.TOKEN_CACHE_TIME_SECONDS);
        return userMap;
    }

    @Override
    public WebDataBean getWebDataBean() {
        return webDataBeanDao.getOnlyData();
    }

}

