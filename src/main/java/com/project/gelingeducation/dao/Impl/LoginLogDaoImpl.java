package com.project.gelingeducation.dao.Impl;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.dao.ILoginLogDao;
import com.project.gelingeducation.domain.LoginLog;
import com.project.gelingeducation.domain.Permission;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class LoginLogDaoImpl extends BaseDao implements ILoginLogDao {

    @Override
    public void insert(LoginLog loginLog) {
        getSession().save(loginLog);
    }

    @Override
    public List<LoginLog> queryAll() {
        String hql = "from LoginLog";
        return getSession().createQuery(hql).getResultList();
    }

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

    @Override
    public LoginLog getByUid(Long uid) {
        Query query = getSession().createQuery("from LoginLog where uid=?0");
        query.setParameter(0, uid);
        LoginLog loginLog = (LoginLog) query.uniqueResult();
        return loginLog;
    }

    @Override
    public List getLoginLogByIp(String ip) {
        Query query = getSession().createQuery("from LoginLog where ip =?0");
        query.setParameter(0, ip);
        return query.list();
    }


}
