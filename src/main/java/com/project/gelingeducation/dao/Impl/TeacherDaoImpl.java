package com.project.gelingeducation.dao.Impl;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.dao.ITeacherDao;
import com.project.gelingeducation.domain.Teacher;
import com.project.gelingeducation.domain.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class TeacherDaoImpl extends BaseDao implements ITeacherDao {

    @Override
    public void insert(Teacher teacher) {
        getSession().save(teacher);
    }

    @Override
    public Teacher findById(long id) {
        return getSession().get(Teacher.class, id);
    }

    @Override
    public PageResult getLists(int currentPage, int pageSize) {
        Session session = getSession();

        String hql = "select count(*) from Teacher";
        Query queryCount = session.createQuery(hql);
        long allrows = (long) queryCount.uniqueResult();

        TypedQuery<User> query = session.createQuery("from Teacher");
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
