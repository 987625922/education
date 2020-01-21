package com.project.gelingeducation.dao.Impl;

import com.project.gelingeducation.dao.AdminInfoDao;
import com.project.gelingeducation.domain.AdminInfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.TypedQuery;
import java.util.List;

public class AdminInfoDaoImpl implements AdminInfoDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }


    @Override
    public List<AdminInfo> findAll() {
        TypedQuery<AdminInfo> query = getSession().createQuery("from admim");
        return query.getResultList();

    }

    @Override
    public AdminInfo findById(long id) {
        AdminInfo adminInfo = getSession().get(AdminInfo.class, id);
        return adminInfo;
    }

    @Override
    public long insert(AdminInfo adminInfo) {
        getSession().save(adminInfo);
        return adminInfo.getId();
    }

    @Override
    public void delect(long id) {
        getSession().delete(getSession().get(AdminInfo.class, id));
    }

    @Override
    public void update(AdminInfo adminInfo) {
        getSession().update(adminInfo);
    }
}
