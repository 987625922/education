package com.project.gelingeducation.dao.Impl;

import com.project.gelingeducation.dao.IRoleDao;
import com.project.gelingeducation.domain.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class RoleDaoImpl implements IRoleDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }


    @Override
    public void insert(Role role) {
        getSession().save(role);
    }

    @Override
    public Role findById(long id) {
        return getSession().get(Role.class,id);
    }


}
