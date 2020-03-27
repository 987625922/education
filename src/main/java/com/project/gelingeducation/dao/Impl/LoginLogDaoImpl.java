package com.project.gelingeducation.dao.Impl;

import com.project.gelingeducation.dao.LoginLogDao;
import com.project.gelingeducation.domain.LoginLog;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

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

    @Override
    public List<LoginLog> list() {
        String hql = "from LoginLog";
        TypedQuery<LoginLog> query = getSession().createQuery(hql);
        List<LoginLog> list = query.getResultList();
        return list;
    }

    @Override
    public LoginLog getByUid(long uid) {
        Query query = getSession().createQuery("from LoginLog where uid=?0");
        query.setParameter(0, uid);
//        List<LoginLog> list = query.list();
        LoginLog loginLog = (LoginLog) query.uniqueResult();
        return loginLog;
    }
}
