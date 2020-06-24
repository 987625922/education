package com.project.gelingeducation.dao.Impl;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.common.utils.BeanUtil;
import com.project.gelingeducation.dao.IUserDao;
import com.project.gelingeducation.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Slf4j
@Repository
public class UserDaoImpl extends BaseDao implements IUserDao {

    @Override
    public PageResult queryAll(Integer currentPage, Integer pageSize) {
        Session session = getSession();

        String hql = "select count(*) from User";//此处的Product是对象
        Query queryCount = session.createQuery(hql);
        long allrows = (long) queryCount.uniqueResult();

        TypedQuery<User> query = session.createQuery("from User");
        query.setFirstResult((currentPage - 1) * pageSize);//得到当前页
        query.setMaxResults(pageSize);//得到每页的记录数

        long totalPage = (allrows - 1) / pageSize + 1;
        List<User> list = query.getResultList();

        PageResult pageResult = new PageResult();
        pageResult.setTotalPages(totalPage);
        pageResult.setTotalRows(allrows);
        pageResult.setLists(list);
        pageResult.setCurrentPage(currentPage);
        pageResult.setPageSize(pageSize);

        return pageResult;
    }

    @Override
    public List<User> queryAll() {
        return getSession().createQuery("FROM User").list();
    }

    @Override
    public User findByPhone(String account) {
        Query query = getSession().createQuery("from User where account=?0");
        query.setParameter(0, account);
        return (User) query.uniqueResult();
    }

    @Override
    public User findById(Long id) {
        return getSession().get(User.class, id);
    }

    @Override
    public User insert(User user) {
        getSession().save(user);
        return user;
    }

    @Override
    public void delect(Long id) {
        getSession().createQuery("DELETE FROM User WHERE id = " + id).executeUpdate();
    }

    @Override
    public void delSel(String ids) {
        Query query = getSession().createQuery
                ("DELETE FROM User WHERE id in(" + ids + ")");
        query.executeUpdate();
    }


    @Override
    public void update(User user) {
        Session session = getSession();
        User findUser = session.get(User.class, user.getId());
        BeanUtil.copyPropertiesIgnoreNull(user, findUser);
        session.update(findUser);
    }

    @Override
    public void updateCoverImg(Long id, String coverImg) {
        Query query = getSession().createQuery("UPDATE User set coverImg = " + coverImg + " where id = " + id);
        query.executeUpdate();
    }

    @Override
    public PageResult selbyname(String name, Integer currentPage, Integer pageSize) {
        Session session = getSession();
        String hql = "select count(*) from User where userName LIKE '%" + name + "%'";//此处的Product是对象
        Query queryCount = session.createQuery(hql);
        Long allrows = (Long) queryCount.uniqueResult();

        TypedQuery<User> query = session.createQuery("from User where userName LIKE '%" + name + "%'");
        query.setFirstResult((currentPage - 1) * pageSize);//得到当前页
        query.setMaxResults(currentPage * pageSize);//得到每页的记录数

        Long totalPage = (allrows - 1) / pageSize + 1;
        List<User> list = query.getResultList();

        PageResult pageResult = new PageResult();
        pageResult.setTotalPages(totalPage);
        pageResult.setTotalRows(allrows);
        pageResult.setLists(list);
        pageResult.setCurrentPage(currentPage + 1);
        pageResult.setPageSize(pageSize);

        return pageResult;
    }


}
