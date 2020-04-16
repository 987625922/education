package com.project.gelingeducation.dao.Impl;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.dao.IRoleDao;
import com.project.gelingeducation.domain.Role;
import com.project.gelingeducation.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
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

    @Override
    public void delRoleById(long id) {
        getSession().delete(getSession().get(Role.class, id));
    }

    @Override
    public long getCount() {
        String hql = "select count(*) from Role";//此处的Product是对象
        Query queryCount = getSession().createQuery(hql);
        long allrows = (long) queryCount.uniqueResult();
        return allrows;
    }

    @Override
    public PageResult getRolePageList(int currentPage, int pageSize) {
        Session session = getSession();

        String hql = "select count(*) from Role";//此处的Product是对象
        Query queryCount = session.createQuery(hql);
        long allrows = (long) queryCount.uniqueResult();

        TypedQuery<User> query = session.createQuery("from Role");
        query.setFirstResult((currentPage - 1) * pageSize);//得到当前页
        query.setMaxResults(pageSize);//得到每页的记录数

        long totalPage = (allrows - 1) / pageSize + 1;
        List<User> list = query.getResultList();

        PageResult pageResult = new PageResult();
        pageResult.setTotalPages(totalPage);
        pageResult.setTotalRows(allrows);
        pageResult.setLists(list);
        pageResult.setPageNum(currentPage);
        pageResult.setPageSize(pageSize);
        return pageResult;
    }


    @Override
    public Role findDefault() {
        Query query = getSession().createQuery("from Role where is_default=?0");
        query.setParameter(0, 1);
        Role role = (Role) query.uniqueResult();
        return role;
    }

}
