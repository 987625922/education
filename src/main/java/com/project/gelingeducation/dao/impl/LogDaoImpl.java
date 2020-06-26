package com.project.gelingeducation.dao.impl;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.dao.ILogDao;
import com.project.gelingeducation.entity.Course;
import com.project.gelingeducation.entity.Log;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author: LL
 * @Description: 项目错误日志的Dao
 * 备注：
 */
@Repository
public class LogDaoImpl extends BaseDao implements ILogDao {

    /**
     * 分页查询
     *
     * @return /
     */
    @Override
    public PageResult queryAll(Integer currentPage, Integer pageSize) {
        Session session = getSession();

        String hql = "select count(*) from Log";
        Query queryCount = session.createQuery(hql);
        Long allrows = (Long) queryCount.uniqueResult();
        TypedQuery<Course> query = session.createQuery("from Log");
        query.setFirstResult((currentPage - 1) * pageSize);//得到当前页
        query.setMaxResults(pageSize);//得到每页的记录数
        Long totalPage = (allrows - 1) / pageSize + 1;
        List<Course> list = query.getResultList();
        PageResult pageResult = new PageResult();
        pageResult.setTotalPages(totalPage);
        pageResult.setTotalRows(allrows);
        pageResult.setLists(list);
        pageResult.setCurrentPage(currentPage);
        pageResult.setPageSize(pageSize);
        return pageResult;
    }

    /**
     * 所有查询
     *
     * @return /
     */
    @Override
    public List queryAll() {
        return getSession().createQuery("FROM Log").getResultList();
    }

    /**
     * 保存日志数据
     *
     * @param log 日志实体
     */
    @Override
    public void save(Log log) {
        getSession().save(log);
    }

    /**
     * 根据id查询异常详情
     *
     * @param id 日志ID
     * @return Object
     */
    @Override
    public Log findByErrDetail(Long id) {
        return getSession().get(Log.class, id);
    }

    /**
     * 导出日志
     *
     * @param logs     待导出的数据
     * @param response /
     * @throws IOException /
     */
    @Override
    public void download(List<Log> logs, HttpServletResponse response) {

    }

    /**
     * 删除所有error日志
     */
    @Override
    public void delAllByError() {
        getSession().createQuery("DELETE FROM Log WHERE logeType = ERROR");
    }

    /**
     * 删除所有INFO日志
     */
    @Override
    public void delAllByInfo() {
        getSession().createQuery("DELETE FROM Log WHERE logeType = INFO");
    }

    /**
     * 根据id删除
     *
     * @param id logId
     */
    @Override
    public void delOneLog(Long id) {
        getSession().createQuery("DELETE FROM Log WHERE id = " + id);
    }
}
