package com.project.gelingeducation.dao.Impl;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.dao.IPermissionDao;
import com.project.gelingeducation.entity.Permission;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class PermissionDaoImpl extends BaseDao implements IPermissionDao {


    @Override
    public void insertPermission(Permission permission) {
        getSession().save(permission);
    }

    @Override
    public Permission getById(Long id) {
        return getSession().get(Permission.class,id);
    }

    @Override
    public List queryAll() {
        return getSession().createQuery("from Permission").list();
    }

    @Override
    public PageResult queryAll(Integer currentPage, Integer pageSize) {
        Session session = getSession();

        String hql = "select count(*) from Permission";
        Query queryCount = session.createQuery(hql);
        long allrows = (long) queryCount.uniqueResult();

        TypedQuery<Permission> query = session.createQuery("from Permission");
        query.setFirstResult((currentPage - 1) * pageSize);//得到当前页
        query.setMaxResults(pageSize);//得到每页的记录数

        long totalPage = (allrows - 1) / pageSize + 1;
        List<Permission> list = query.getResultList();

        PageResult pageResult = new PageResult();
        pageResult.setTotalPages(totalPage);
        pageResult.setTotalRows(allrows);
        pageResult.setLists(list);
        pageResult.setCurrentPage(currentPage);
        pageResult.setPageSize(pageSize);

        return pageResult;
    }


    @Override
    public List<Permission> getPermissionListByIds(Long[] ids) {
        String sql = "";
        for (int i = 0; i < ids.length; i++) {
            if (i == 0) {
                sql = sql + ids[i];
            } else {
                sql = sql + "," + ids[i];
            }
        }
        Query query = getSession().createQuery("from Permission WHERE id in (" + sql + ")");
        return query.list();
    }


}
