package com.project.gelingeducation.dao.Impl;

import com.project.gelingeducation.dao.AdminInfoDao;
import com.project.gelingeducation.domain.AdminInfo;
import com.project.gelingeducation.dto.PageResult;
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
public class AdminInfoDaoImpl implements AdminInfoDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }


    @Override
    public PageResult getLists(int page, int limits) {
        Session session = getSession();

        TypedQuery<AdminInfo> query = session.createQuery("from AdminInfo");
        query.setFirstResult(page);//得到当前页
        query.setMaxResults(limits);//得到每页的记录数

        String hql = "select count(*) from AdminInfo";//此处的Product是对象
        Query queryCount = session.createQuery(hql);

        PageResult pageResult = new PageResult();
        pageResult.setTotalPages(((Long) queryCount.setCacheable(true).uniqueResult()).intValue());
        List<AdminInfo> list = query.getResultList();
        pageResult.setLists(list);
        pageResult.setPageNum(page);
        pageResult.setPageSize(limits);

        return pageResult;
    }

    @Override
    public AdminInfo findByPhone(String account) {
        Query query = getSession().createQuery("from AdminInfo where account=?0");
        query.setParameter(0, account);
        List<AdminInfo> list = query.list();
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }

    }

    @Override
    public AdminInfo findById(long id) {
        AdminInfo adminInfo = getSession().get(AdminInfo.class, id);
        return adminInfo;
    }

    @Override
    public AdminInfo insert(AdminInfo adminInfo) {
        getSession().save(adminInfo);
        return adminInfo;
    }

    @Override
    public void delect(long id) {
        getSession().delete(getSession().get(AdminInfo.class, id));
    }

    @Override
    public void update(AdminInfo adminInfo) {
//        getSession().update(adminInfo);
        StringBuffer hql = new StringBuffer("update AdminInfo set ");
        int index = 0;
        if (!StringUtils.isEmpty(adminInfo.getUserName())) {
            hql.append("user_name = '" + adminInfo.getUserName() + "'");
            index++;
        }
        if (!StringUtils.isEmpty(adminInfo.getAccount())) {
            if (index > 0) {
                hql.append(" ,");
            }
            hql.append("account = '" + adminInfo.getAccount() + "'");
            index++;
        }

        if (!StringUtils.isEmpty(adminInfo.getPassword())) {
            if (index > 0) {
                hql.append(" ,");
            }
            hql.append("password = '" + adminInfo.getPassword() + "'");
            index++;
        }
        if (!StringUtils.isEmpty(adminInfo.getCoverImg())) {
            if (index > 0) {
                hql.append(" ,");
            }
            hql.append("cover_img = '" + adminInfo.getCoverImg() + "'");
            index++;
        }
        if (!StringUtils.isEmpty(adminInfo.getIsAdaim())) {
            if (index > 0) {
                hql.append(" ,");
            }
            hql.append("isAdaim = '" + adminInfo.getIsAdaim() + "'");
            index++;
        }
        if (!StringUtils.isEmpty(adminInfo.getEMail())) {
            if (index > 0) {
                hql.append(" ,");
            }
            hql.append("email = '" + adminInfo.getEMail() + "'");
            index++;
        }
        if (!StringUtils.isEmpty(adminInfo.getSex())) {
            if (index > 0) {
                hql.append(" ,");
            }
            hql.append("ssex = '" + adminInfo.getSex() + "'");
            index++;
        }
        if (!StringUtils.isEmpty(adminInfo.getNote())) {
            if (index > 0) {
                hql.append(" ,");
            }
            hql.append("note = '" + adminInfo.getNote() + "'");
        }
        if (!StringUtils.isEmpty(adminInfo.getStatus())) {
            if (index > 0) {
                hql.append(" ,");
            }
            hql.append("status = '" + adminInfo.getStatus() + "'");
        }
        if (!StringUtils.isEmpty(adminInfo.getCreateTime())) {
            if (index > 0) {
                hql.append(" ,");
            }
            hql.append("create_time = '" + adminInfo.getCreateTime() + "'");
        }
        if (!StringUtils.isEmpty(adminInfo.getModifyTime())) {
            if (index > 0) {
                hql.append(" ,");
            }
            hql.append("modify_time = '" + adminInfo.getModifyTime() + "'");
        }
        Query query = getSession().createQuery(hql.toString() + " where id = " + adminInfo.getId());
        query.executeUpdate();
    }

    @Override
    public void updateCoverImg(long id, String coverImg) {
//        Query query = getSession().createQuery("update AdminInfo set coverImg = " + coverImg + " where id = " + id);
//        query.executeUpdate();
        Session session = getSession();
        AdminInfo adminInfo = session.get(AdminInfo.class, id);
        adminInfo.setCoverImg(coverImg);
    }


    @Override
    public void updatePassword(long id, String newPassword) {
        Query query = getSession().createQuery("update AdminInfo set password = '" + newPassword + "' where id = " + id);
        query.executeUpdate();
    }
}
