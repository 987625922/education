package com.project.gelingeducation.dao.Impl;

import com.project.gelingeducation.dao.LoginLogDao;
import com.project.gelingeducation.domain.LoginLog;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoginLogDaoImpl implements LoginLogDao {


    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }


    @Override
    public void insert(LoginLog loginLog) {
        getSession().save(loginLog);
    }
}
