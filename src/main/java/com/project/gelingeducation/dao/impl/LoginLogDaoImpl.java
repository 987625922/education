package com.project.gelingeducation.dao.impl;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.dao.ILoginLogDao;
import com.project.gelingeducation.entity.LoginLog;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * 登录日志实体类的dao
 *
 * @author LL
 */
@Repository
public class LoginLogDaoImpl extends BaseDao implements ILoginLogDao {

    /**
     * 添加登录日志
     *
     * @param loginLog
     */
    @Override
    public void insert(LoginLog loginLog) {
        getSession().save(loginLog);
    }

    /**
     * 获取所有的登录日志
     *
     * @return
     */
    @Override
    public List<LoginLog> queryAll() {
        String hql = "from LoginLog";
        return getSession().createQuery(hql).getResultList();
    }

    /**
     * 分页获取登录日志
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public PageResult queryAll(Integer currentPage, Integer pageSize) {
        Session session = getSession();

        String hql = "select count(*) from LoginLog";
        Query queryCount = session.createQuery(hql);
        long allrows = (long) queryCount.uniqueResult();

        TypedQuery<LoginLog> query = session.createQuery("from LoginLog");
        query.setFirstResult((currentPage - 1) * pageSize);//得到当前页
        query.setMaxResults(pageSize);//得到每页的记录数

        long totalPage = (allrows - 1) / pageSize + 1;
        List<LoginLog> list = query.getResultList();

        PageResult pageResult = new PageResult();
        pageResult.setTotalPages(totalPage);
        pageResult.setTotalRows(allrows);
        pageResult.setLists(list);
        pageResult.setCurrentPage(currentPage);
        pageResult.setPageSize(pageSize);

        return pageResult;
    }

    /**
     * 根据用户id获取登录日志
     *
     * @param uid
     * @return
     */
    @Override
    public LoginLog getByUid(Long uid) {
        Query query = getSession().createQuery("from LoginLog where uid=?0");
        query.setParameter(0, uid);
        LoginLog loginLog = (LoginLog) query.uniqueResult();
        return loginLog;
    }

    /**
     * 根据ip获取登录日志
     *
     * @param ip
     * @return
     */
    @Override
    public List<LoginLog> getLoginLogByIp(String ip) {
        Query<LoginLog> query = getSession()
                .createQuery("from LoginLog where ip =?0");
        query.setParameter(0, ip);
        return query.list();
    }


}
