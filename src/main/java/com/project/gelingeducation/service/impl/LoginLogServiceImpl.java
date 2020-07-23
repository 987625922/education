package com.project.gelingeducation.service.impl;

import com.project.gelingeducation.common.utils.HttpUtil;
import com.project.gelingeducation.common.utils.SpringUtil;
import com.project.gelingeducation.dao.ILoginLogDao;
import com.project.gelingeducation.entity.LoginLog;
import com.project.gelingeducation.entity.User;
import com.project.gelingeducation.service.ILoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @Author: LL
 * @Description: 登录日志的service
 * 备注：
 */
@Service
@Transactional(readOnly = true)
public class LoginLogServiceImpl implements ILoginLogService {

    /**
     * 登录日志的dao
     */
    @Autowired
    private ILoginLogDao loginLogDao;

    /**
     * 添加登录日志
     *
     * @param loginLog
     */
    @Override
    public void insert(LoginLog loginLog) {
        loginLog.setLoginTime(new Date());
        String ip = HttpUtil.getCityInfo(HttpUtil.getIp(SpringUtil.getHttpServletRequest()));
        loginLog.setIp(ip);
        loginLog.setLocation(HttpUtil.getCityInfo(ip));
        loginLogDao.insert(loginLog);
    }

    /**
     * 搜索全部登录日志的实体类list
     *
     * @param currentPage 页码
     * @param pageSize    页数
     * @return 页码为空返回全都list，不为空返回分页实体类
     */
    @Override
    public Object queryAll(Integer currentPage, Integer pageSize) {
        if (currentPage != null && pageSize != null) {
            return loginLogDao.queryAll(currentPage, pageSize);
        } else {
            return loginLogDao.queryAll();
        }
    }

    /**
     * 获取登录日志
     *
     * @param uid 用户id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public LoginLog getByUserId(Long uid) {
        return loginLogDao.getByUid(uid);
    }

    /**
     * 添加或者更新用户登录日志实体类
     * 如果有了登录日志就更新，没有就插入
     *
     * @param user 用户实体类
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveOrUpdateLoginLogByUid(User user, HttpServletRequest request) {
        LoginLog loginLog = user.getLoginLog();
        //获取ip地址
        String ip = HttpUtil.getIp(request);
        //查看账号是否有登录日志，有就更新，没有就插入
        if (loginLog != null) {
            loginLog.setLastLoginTime(loginLog.getLoginTime());
            loginLog.setLocation(HttpUtil.getCityInfo(ip));
            loginLog.setBrowser(HttpUtil.getBrowser(request));
            loginLog.setLoginTime(new Date());
            loginLog.setUserSystem(HttpUtil.getOsName(request));
            loginLog.setIp(ip);
            loginLogDao.update(loginLog);
        } else {
            loginLog = new LoginLog();
            loginLog.setUser(user);
            loginLog.setLoginTime(new Date());
            loginLog.setBrowser(HttpUtil.getBrowser(request));
            loginLog.setIp(ip);
            loginLog.setLocation(HttpUtil.getCityInfo(ip));
            loginLog.setUserSystem(HttpUtil.getOsName(request));
            loginLogDao.insert(loginLog);
        }
    }

    @Override
    public List<LoginLog> getLoginLogByIp(String ip) {
        return loginLogDao.getLoginLogByIp(ip);
    }
}
