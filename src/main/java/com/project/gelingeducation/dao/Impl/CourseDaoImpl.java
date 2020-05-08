package com.project.gelingeducation.dao.Impl;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.dao.ICourseDao;
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
public class CourseDaoImpl implements ICourseDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public PageResult findAll() {
        Session session = getSession();

        PageResult pageResult = new PageResult();
        TypedQuery<Course> query = session.createQuery("from Course");
        pageResult.setLists(query.getResultList());

        String hql = "select count(*) from Course";
        Query queryCount = session.createQuery(hql);
        long allrows = (long) queryCount.uniqueResult();
        pageResult.setTotalRows(allrows);

        return pageResult;
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

        String hql = "select count(*) from Course";
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


    @Override
    public void delSel(long[] ids) {
        String sql = "";
        for (int i = 0; i < ids.length; i++) {
            if (i == 0) {
                sql = sql + ids[i];
            } else {
                sql = sql + "," + ids[i];
            }
        }
        Query query = getSession().createQuery("DELETE FROM Course WHERE id in(" + sql + ")");
        query.executeUpdate();
    }

    @Override
    public PageResult selbyname(String name, int currentPage, int pageSize) {
        Session session = getSession();

        String hql = "select count(*) from Course where name LIKE '%" + name + "%'";//此处的Product是对象
        Query queryCount = session.createQuery(hql);
        long allrows = (long) queryCount.uniqueResult();

        TypedQuery<Course> query = session.createQuery("from Course where" +
                " name LIKE '%" + name + "%'");
        query.setFirstResult((currentPage - 1) * pageSize);//得到当前页
        query.setMaxResults(currentPage * pageSize);//得到每页的记录数

        long totalPage = (allrows - 1) / pageSize + 1;
        List<Course> list = query.getResultList();

        PageResult pageResult = new PageResult();
        pageResult.setTotalPages(totalPage);
        pageResult.setTotalRows(allrows);
        pageResult.setLists(list);
        pageResult.setPageNum(currentPage + 1);
        pageResult.setPageSize(pageSize);

        return pageResult;
    }

    @Override
    public PageResult selByNameOrStatusOrPriceOrTeacher(String name, int status,
                                                        double startPrice, double endPrice,
                                                        long teacherId, int currentPage,
                                                        int pageSize) {
        Session session = getSession();

        StringBuffer hql = new StringBuffer("from Course as course");
        if (teacherId != -1) {
            hql.append(" inner join fetch course.teachers as teacher where teacher.id = " + teacherId);
        }
        if (teacherId == -1 && status != -1 && startPrice != -1 && endPrice != -1) {
            hql.append(" where 1=1");
        }
        if (status != -1) {
            hql.append(" AND course.status = " + status);
        }
        if (startPrice != -1 && endPrice != -1) {
            hql.append(" AND course.startPrice = " + startPrice + " AND course.endPrice = " + endPrice);
        }
        hql.append(" AND course.name LIKE '%" + name + "%'");
        TypedQuery<Course> query = session.createQuery(hql.toString());

        query.setFirstResult((currentPage - 1) * pageSize);//得到当前页
        query.setMaxResults(currentPage * pageSize);//得到每页的记录数
        List<Course> list = query.getResultList();
//
        hql.insert(0, "select count(*) ");//此处的Product是对象
        Query queryCount = session.createQuery(hql.toString());
//        long allrows = (long) queryCount.uniqueResult();
//        long totalPage = (allrows - 1) / pageSize + 1;

        PageResult pageResult = new PageResult();
//        pageResult.setTotalPages(totalPage);
//        pageResult.setTotalRows(allrows);
        pageResult.setLists(list);
        pageResult.setPageNum(currentPage + 1);
        pageResult.setPageSize(pageSize);

        return pageResult;
    }


}
