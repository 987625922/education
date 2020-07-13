package com.project.gelingeducation.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

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
     *
     * @return hibernate的session
     */
    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * 更新一个实体
     *
     * @param obj
     */
    protected void baseUpdate(Object obj) {
        getSession().update(obj);
    }

    /**
     * 更新一个实体并马上提交到数据库
     *
     * @param object
     */
    protected void updateAndFlush(Object object) {
        getSession().update(object);
        getSession().flush();
    }

    /**
     * 根据主键返回一个实体
     *
     * @param clazz
     * @param id
     * @return
     */
    public Object get(Class<?> clazz, Object id) {
        return getSession().get(clazz, (Serializable) id);
    }

    /**
     * hibernate 保存
     *
     * @param object
     */
    public void save(Object object) {
        getSession().merge(object);
    }

    /**
     * hql更新删除语句
     *
     * @param queryStirng
     */
    public void executeUpdate(String queryStirng) {
        getSession()
                .createQuery(queryStirng)
                .executeUpdate();
    }
}
