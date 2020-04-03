package com.project.gelingeducation.dao.Impl;

import com.project.gelingeducation.dao.IRoleDao;
import com.project.gelingeducation.domain.Role;
import com.project.gelingeducation.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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
        return getSession().get(Role.class, id);
    }

    @Override
    public List<Role> list() {
        Query<Role> query = getSession().createQuery("from Role");
        return query.list();
    }


}
