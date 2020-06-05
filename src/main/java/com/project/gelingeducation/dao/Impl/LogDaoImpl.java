package com.project.gelingeducation.dao.Impl;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.dao.ILogDao;
import com.project.gelingeducation.domain.Course;
import com.project.gelingeducation.domain.Log;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Repository
public class LogDaoImpl extends BaseDao implements ILogDao {


    @Override
    public PageResult queryAll(Integer currentPage, Integer pageSize) {
        Session session = getSession();

        String hql = "select count(*) from Log";
        Query queryCount = session.createQuery(hql);
        long allrows = (long) queryCount.uniqueResult();

        TypedQuery<Course> query = session.createQuery("from Log");
        query.setFirstResult((currentPage - 1) * pageSize);//得到当前页
        query.setMaxResults(pageSize);//得到每页的记录数

        long totalPage = (allrows - 1) / pageSize + 1;
        List<Course> list = query.getResultList();

        PageResult pageResult = new PageResult();
        pageResult.setTotalPages(totalPage);
        pageResult.setTotalRows(allrows);
        pageResult.setLists(list);
        pageResult.setCurrentPage(currentPage);
        pageResult.setPageSize(pageSize);

        return pageResult;
    }

    @Override
    public void save(Log log) {
        getSession().save(log);
    }

    @Override
    public Log findByErrDetail(Long id) {
        return getSession().get(Log.class, id);
    }

    @Override
    public void download(List<Log> logs, HttpServletResponse response)
            throws IOException {

    }

    @Override
    public void delAllByError() {
        String hql = "DELETE FROM Log WHERE logeType = ERROR";
        getSession().createQuery(hql);
    }

    @Override
    public void delAllByInfo() {
        String hql = "DELETE FROM Log WHERE logeType = INFO";
        getSession().createQuery(hql);
    }
}
