package com.project.gelingeducation.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: LL
 * @Description: dao基类
 */
abstract class BaseDao {

    /**
     * hibernate的session工厂
     */
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * 获取线程上ThreadLocal的hibernate session
     * @return hibernate的session
     */
    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

}
