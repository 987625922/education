package com.project.gelingeducation.service.Impl;

import com.project.gelingeducation.dao.LoginLogDao;
import com.project.gelingeducation.domain.LoginLog;
import com.project.gelingeducation.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoginLogServiceImpl implements LoginLogService {

    @Autowired
    private LoginLogDao loginLogDao;

    @Override
    public void insert(LoginLog loginLog) {
        loginLogDao.insert(loginLog);
    }
}
