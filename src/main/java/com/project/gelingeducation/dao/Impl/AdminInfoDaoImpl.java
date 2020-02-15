package com.project.gelingeducation.dao.Impl;

import com.project.gelingeducation.dao.AdminInfoDao;
import com.project.gelingeducation.domain.AdminInfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class AdminInfoDaoImpl implements AdminInfoDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }


    @Override
    public List<AdminInfo> findAll() {
        TypedQuery<AdminInfo> query = getSession().createQuery("from admin");
        return query.getResultList();

    }

    @Override
    public AdminInfo findById(long id) {
        AdminInfo adminInfo = getSession().get(AdminInfo.class, id);
        return adminInfo;
    }

    @Override
    public AdminInfo findByPhone(String phone) {
        Query query = getSession().createQuery("from AdminInfo where phone=?");
        query.setParameter(0, phone);
        List<AdminInfo> list = query.list();
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public AdminInfo insert(AdminInfo adminInfo) {
        getSession().save(adminInfo);
        return adminInfo;
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
