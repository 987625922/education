package com.project.gelingeducation.dao.Impl;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.dao.ISubjectDao;
import com.project.gelingeducation.domain.Course;
import com.project.gelingeducation.domain.Subject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;


@Repository
public class SubjectDaoImpl extends BaseDao implements ISubjectDao {

    @Override
    public PageResult queryAll(Integer currentPage,Integer pageSize) {
        Session session = getSession();

        String hql = "select count(*) from Subject";
        Query queryCount = session.createQuery(hql);
        long allrows = (long) queryCount.uniqueResult();

        TypedQuery<Subject> query = session.createQuery("from Subject");
        query.setFirstResult((currentPage - 1) * pageSize);//得到当前页
        query.setMaxResults(pageSize);//得到每页的记录数

        long totalPage = (allrows - 1) / pageSize + 1;
        List<Subject> list = query.getResultList();

        PageResult pageResult = new PageResult();
        pageResult.setTotalPages(totalPage);
        pageResult.setTotalRows(allrows);
        pageResult.setLists(list);
        pageResult.setCurrentPage(currentPage);
        pageResult.setPageSize(pageSize);

        return pageResult;
    }

    @Override
    public List<Subject> queryAll() {
        return getSession().createQuery("FROM Subject").list();
    }

    @Override
    public Subject findById(Long id) {
        return getSession().get(Subject.class, id);
    }

    @Override
    public Subject insert(Subject subject) {
        getSession().save(subject);
        return subject;
    }

    @Override
    public void delect(Long id) {
        getSession().createQuery("DELETE From Subject WHERE id = " + id).executeUpdate();
    }

    @Override
    public void update(Subject subject) {
        getSession().update(subject);
    }
}
