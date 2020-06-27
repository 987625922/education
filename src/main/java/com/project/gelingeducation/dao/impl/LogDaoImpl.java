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
        PageResult pageResult = new PageResult();

        String hql = "select count(*) from Log";
        Query queryCount = session.createQuery(hql);
        Long allrows = (Long) queryCount.uniqueResult();

        TypedQuery<Course> query = session.createQuery("from Log ORDER BY id DESC");
        query.setFirstResult((currentPage - 1) * pageSize);//得到当前页
        query.setMaxResults(pageSize);//得到每页的记录数

        pageResult.setTotalRows(allrows).setCurrentPage(currentPage).setPageSize(pageSize)
                .setTotalPages((allrows - 1) / pageSize + 1).setLists(query.getResultList());
        return pageResult;
    }

    /**
     * 所有查询
     *
     * @return /
     */
    @Override
    public List queryAll() {
        return getSession().createQuery("FROM Log ORDER BY id DESC").getResultList();
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
        getSession().createQuery("DELETE FROM Log WHERE id = " + id).executeUpdate();
    }

    /**
     * 解决了一个日志
     *
     * @param id logId
     */
    @Override
    public void solveOne(Long id) {
        getSession().createQuery("UPDATE Log WHERE id = " + id + " SET isSolve = 0").executeUpdate();
    }

    /**
     * 把解决的日志标识为未解决
     *
     * @param id logId
     */
    @Override
    public void recurrentOne(Long id) {
        getSession().createQuery("UPDATE Log SET isSolve = 1 WHERE id = " + id).executeUpdate();
    }

    /**
     * 获取未解决的日志
     *
     * @param currentPage
     * @param pageSize
     * @return 分页的日志list
     */
    @Override
    public Object queryNoSolveLog(Integer currentPage, Integer pageSize) {
        Session session = getSession();
        PageResult pageResult = new PageResult();

        Long allRows = (Long) session.createQuery("SELECT COUNT(*) FROM Log").uniqueResult();

        TypedQuery<Course> query = session.createQuery("from Log WHERE isSolve <> 0 ORDER BY id DESC");
        query.setFirstResult((currentPage - 1) * pageSize);//得到当前页
        query.setMaxResults(pageSize);//得到每页的记录数

        pageResult.setTotalRows(allRows).setCurrentPage(currentPage).setPageSize(pageSize)
                .setTotalPages((allRows - 1) / pageSize + 1).setLists(query.getResultList());
        return pageResult;
    }

    /**
     * 获取已解决的日志
     *
     * @param currentPage
     * @param pageSize
     * @return 分页的日志list
     */
    @Override
    public Object querySolveLog(Integer currentPage, Integer pageSize) {
        Session session = getSession();
        PageResult pageResult = new PageResult();

        Long allRows = (Long) session.createQuery("SELECT COUNT(*) FROM Log").uniqueResult();

        TypedQuery<Course> query = session.createQuery("from Log WHERE isSolve = 0 ORDER BY id DESC");
        query.setFirstResult((currentPage - 1) * pageSize);//得到当前页
        query.setMaxResults(pageSize);//得到每页的记录数

        pageResult.setTotalRows(allRows).setCurrentPage(currentPage).setPageSize(pageSize)
                .setTotalPages((allRows - 1) / pageSize + 1).setLists(query.getResultList());
        return pageResult;
    }

    @Override
    public void delMore(String ids) {
        getSession().createQuery("DELETE FROM Log WHERE id IN (" + ids + ")").executeUpdate();
    }
}
