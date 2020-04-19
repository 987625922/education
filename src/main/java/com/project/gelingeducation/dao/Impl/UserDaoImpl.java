package com.project.gelingeducation.dao.Impl;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.dao.IUserDao;
import com.project.gelingeducation.domain.Role;
import com.project.gelingeducation.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.TypedQuery;
import java.util.List;

@Slf4j
@Repository
public class UserDaoImpl implements IUserDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }


    @Override
    public PageResult getLists(int currentPage, int pageSize) {
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
        pageResult.setPageNum(currentPage);
        pageResult.setPageSize(pageSize);

        return pageResult;
    }

    @Override
    public User findByPhone(String account) {
        Query query = getSession().createQuery("from User where account=?0");
        query.setParameter(0, account);
        User user = (User) query.uniqueResult();
        return user;
    }

    @Override
    public User findById(long id) {
        User user = getSession().get(User.class, id);
        return user;
    }

    @Override
    public User insert(User user) {
        getSession().save(user);
        return user;
    }

    @Override
    public void delect(long id) {
        getSession().delete(getSession().get(User.class, id));
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
        Query query = getSession().createQuery("DELETE FROM User WHERE id in(" + sql + ")");
        query.executeUpdate();
    }


    @Override
    public void update(User user) {
        StringBuffer hql = new StringBuffer("update User set ");
        int index = 0;
        if (!StringUtils.isEmpty(user.getUserName())) {
            hql.append("user_name = '" + user.getUserName() + "'");
            index++;
        }
        if (!StringUtils.isEmpty(user.getAccount())) {
            if (index > 0) {
                hql.append(" ,");
            }
            hql.append("account = '" + user.getAccount() + "'");
            index++;
        }

        if (!StringUtils.isEmpty(user.getPassword())) {
            if (index > 0) {
                hql.append(" ,");
            }
            hql.append("password = '" + user.getPassword() + "'");
            index++;
        }
        if (!StringUtils.isEmpty(user.getCoverImg())) {
            if (index > 0) {
                hql.append(" ,");
            }
            hql.append("cover_img = '" + user.getCoverImg() + "'");
            index++;
        }
        if (!StringUtils.isEmpty(user.getEMail())) {
            if (index > 0) {
                hql.append(" ,");
            }
            hql.append("email = '" + user.getEMail() + "'");
            index++;
        }
        if (!StringUtils.isEmpty(user.getSex())) {
            if (index > 0) {
                hql.append(" ,");
            }
            hql.append("ssex = '" + user.getSex() + "'");
            index++;
        }
        if (!StringUtils.isEmpty(user.getNote())) {
            if (index > 0) {
                hql.append(" ,");
            }
            hql.append("note = '" + user.getNote() + "'");
        }
        if (!StringUtils.isEmpty(user.getStatus())) {
            if (index > 0) {
                hql.append(" ,");
            }
            hql.append("status = '" + user.getStatus() + "'");
        }
        if (!StringUtils.isEmpty(user.getModifyTime())) {
            if (index > 0) {
                hql.append(" ,");
            }
            hql.append("modify_time = '" + user.getModifyTime() + "'");
        }
        Query query = getSession().createQuery(hql.toString() + " where id = " + user.getId());
        query.executeUpdate();
    }

    @Override
    public void updateCoverImg(long id, String coverImg) {
        Query query = getSession().createQuery("update User set coverImg = " + coverImg + " where id = " + id);
        query.executeUpdate();
    }

    @Override
    public PageResult selbyname(String name, int currentPage, int pageSize) {
//        Query queryCount = session.createQuery("from User where userName LIKE '%"+name+"%'");

        Session session = getSession();

        String hql = "select count(*) from User where userName LIKE '%" + name + "%'";//此处的Product是对象
        Query queryCount = session.createQuery(hql);
        long allrows = (long) queryCount.uniqueResult();

        TypedQuery<User> query = session.createQuery("from User where userName LIKE '%" + name + "%'");
        query.setFirstResult((currentPage - 1) * pageSize);//得到当前页
        query.setMaxResults(currentPage * pageSize);//得到每页的记录数

        long totalPage = (allrows - 1) / pageSize + 1;
        List<User> list = query.getResultList();

        PageResult pageResult = new PageResult();
        pageResult.setTotalPages(totalPage);
        pageResult.setTotalRows(allrows);
        pageResult.setLists(list);
        pageResult.setPageNum(currentPage + 1);
        pageResult.setPageSize(pageSize);

        return pageResult;
    }


}
