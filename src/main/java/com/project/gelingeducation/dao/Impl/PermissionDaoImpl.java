package com.project.gelingeducation.dao.Impl;

import com.project.gelingeducation.dao.IPermissionDao;
import com.project.gelingeducation.domain.Permission;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PermissionDaoImpl implements IPermissionDao {


    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }


    @Override
    public void insertPermission(Permission permission) {
        getSession().save(permission);
    }

    @Override
    public Permission getById(long id) {
        return getSession().get(Permission.class,id);
    }
}
