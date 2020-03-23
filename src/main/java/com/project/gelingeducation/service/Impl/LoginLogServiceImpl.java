package com.project.gelingeducation.service.Impl;

import com.project.gelingeducation.dao.LoginLogDao;
import com.project.gelingeducation.domain.LoginLog;
import com.project.gelingeducation.exception.AllException;
import com.project.gelingeducation.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LoginLogServiceImpl implements LoginLogService {

    @Autowired
    private LoginLogDao loginLogDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = AllException.class)
    public void insert(LoginLog loginLog) {
        loginLogDao.insert(loginLog);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = AllException.class)
    public List<LoginLog> list() {
        return loginLogDao.list();
    }
}
