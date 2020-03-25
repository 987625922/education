package com.project.gelingeducation.service.Impl;

import com.project.gelingeducation.dao.LoginLogDao;
import com.project.gelingeducation.domain.LoginLog;
import com.project.gelingeducation.common.exception.AllException;
import com.project.gelingeducation.service.LoginLogService;
import com.project.gelingeducation.common.utils.AddressUtil;
import com.project.gelingeducation.common.utils.HttpContextUtil;
import com.project.gelingeducation.common.utils.IPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class LoginLogServiceImpl implements LoginLogService {

    @Autowired
    private LoginLogDao loginLogDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = AllException.class)
    public void insert(LoginLog loginLog) {
        loginLog.setLoginTime(new Date());
        String ip = IPUtil.getIpAddr(HttpContextUtil.getHttpServletRequest());
        loginLog.setIp(ip);
        loginLog.setLocation(AddressUtil.getCityInfo(ip));
        loginLogDao.insert(loginLog);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = AllException.class)
    public List<LoginLog> list() {
        return loginLogDao.list();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = AllException.class)
    public LoginLog getByUserId(long uid) {
        return loginLogDao.getByUid(uid);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = AllException.class)
    public void getByUserIdLoginUpdate(long uid) {
        LoginLog loginLog = loginLogDao.getByUid(uid);
//        if (loginLog == null)
        loginLog.setLoginTime(new Date());
        String ip = IPUtil.getIpAddr(HttpContextUtil.getHttpServletRequest());
        loginLog.setIp(ip);
        loginLog.setLocation(AddressUtil.getCityInfo(ip));
    }


}
