package com.project.gelingeducation.dao.Impl;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.dao.CourseDao;
import com.project.gelingeducation.domain.Course;
import com.project.gelingeducation.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;


@Repository
public class CourseDaoImpl implements CourseDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Course> findAll() {
        TypedQuery<Course> query = getSession().createQuery("from Course");
        return query.getResultList();
    }

    @Override
    public Course findById(long id) {
        Course course = getSession().get(Course.class, id);
        return course;
    }

    @Override
    public long insert(Course course) {
        getSession().save(course);
        return course.getId();
    }

    @Override
    public void delect(long id) {
        getSession().delete(getSession().get(Course.class, id));
    }

    @Override
    public void update(Course course) {
        getSession().update(course);
    }

    @Override
    public PageResult getLists(int currentPage, int pageSize) {
        Session session = getSession();

        String hql = "select count(*) from Course";//此处的Product是对象
        Query queryCount = session.createQuery(hql);
        long allrows = (long) queryCount.uniqueResult();

        TypedQuery<User> query = session.createQuery("from Course");
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
}
