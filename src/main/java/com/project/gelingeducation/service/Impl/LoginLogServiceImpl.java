package com.project.gelingeducation.service.Impl;

import com.project.gelingeducation.dao.ILoginLogDao;
import com.project.gelingeducation.domain.LoginLog;
import com.project.gelingeducation.service.LoginLogService;
import com.project.gelingeducation.common.utils.AddressUtil;
import com.project.gelingeducation.common.utils.HttpContextUtil;
import com.project.gelingeducation.common.utils.IPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class LoginLogServiceImpl implements LoginLogService {

    @Autowired
    private ILoginLogDao loginLogDao;

    @Override
    public void insert(LoginLog loginLog) {
        loginLog.setLoginTime(new Date());
        String ip = IPUtil.getIpAddr(HttpContextUtil.getHttpServletRequest());
        loginLog.setIp(ip);
        loginLog.setLocation(AddressUtil.getCityInfo(ip));
        loginLogDao.insert(loginLog);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LoginLog> list() {
        return loginLogDao.list();
    }

    @Override
    @Transactional(readOnly = true)
    public LoginLog getByUserId(long uid) {
        return loginLogDao.getByUid(uid);
    }

    @Override
    public void getByUserIdLoginUpdate(long uid) {
        LoginLog loginLog = loginLogDao.getByUid(uid);
        if (loginLog == null) {
            loginLog = new LoginLog();
            loginLog.setUid(uid);
            loginLog.setLoginTime(new Date());
            String ip = IPUtil.getIpAddr(HttpContextUtil.getHttpServletRequest());
            loginLog.setIp(ip);
            loginLog.setLocation(AddressUtil.getCityInfo(ip));
            loginLogDao.insert(loginLog);
        } else {
            loginLog.setLastLoginTime(loginLog.getLoginTime());
            loginLog.setLoginTime(new Date());
            String ip = IPUtil.getIpAddr(HttpContextUtil.getHttpServletRequest());
            loginLog.setIp(ip);
            loginLog.setLocation(AddressUtil.getCityInfo(ip));
        }
    }


}
