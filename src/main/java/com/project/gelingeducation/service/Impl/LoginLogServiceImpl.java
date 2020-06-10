package com.project.gelingeducation.service.Impl;

import com.project.gelingeducation.common.utils.HttpUtil;
import com.project.gelingeducation.dao.ILoginLogDao;
import com.project.gelingeducation.domain.LoginLog;
import com.project.gelingeducation.service.ILoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional(readOnly = true)
public class LoginLogServiceImpl implements ILoginLogService {

    @Autowired
    private ILoginLogDao loginLogDao;

    @Override
    public void insert(LoginLog loginLog) {
        loginLog.setLoginTime(new Date());
        String ip = HttpUtil.getCityInfo(HttpUtil.getIp(HttpUtil.getHttpServletRequest()));
        loginLog.setIp(ip);
        loginLog.setLocation(HttpUtil.getCityInfo(ip));
        loginLogDao.insert(loginLog);
    }


    @Override
    public Object queryAll(Integer currentPage, Integer pageSize) {
        if (currentPage != null && pageSize != null) {
            return loginLogDao.queryAll(currentPage, pageSize);
        } else {
            return loginLogDao.queryAll();
        }
    }


    @Override
    @Transactional
    public LoginLog getByUserId(Long uid) {
        return loginLogDao.getByUid(uid);
    }

    @Transactional
    @Override
    public void getByUserIdLoginUpdate(Long uid) {
        LoginLog loginLog = loginLogDao.getByUid(uid);
        if (loginLog == null) {
            loginLog = new LoginLog();
            loginLog.setUid(uid);
            loginLog.setLoginTime(new Date());
            String ip = HttpUtil.getCityInfo(HttpUtil.getIp(HttpUtil.getHttpServletRequest()));
            loginLog.setIp(ip);
            loginLog.setLocation(HttpUtil.getCityInfo(ip));
            loginLogDao.insert(loginLog);
        } else {
            loginLog.setLastLoginTime(loginLog.getLoginTime());
            loginLog.setLoginTime(new Date());
            String ip = HttpUtil.getCityInfo(HttpUtil.getIp(HttpUtil.getHttpServletRequest()));
            loginLog.setIp(ip);
            loginLog.setLocation(HttpUtil.getCityInfo(ip));
        }
    }


}
