package com.project.gelingeducation.dao.Impl;

import com.project.gelingeducation.dao.ILoginLogDao;
import com.project.gelingeducation.domain.LoginLog;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<LoginLog> list() {
        String hql = "from LoginLog";
        TypedQuery<LoginLog> query = getSession().createQuery(hql);
        List<LoginLog> list = query.getResultList();
        return list;
    }

    @Override
    public LoginLog getByUid(long uid) {
        Query query = getSession().createQuery("from LoginLog where uid=?0");
        query.setParameter(0, uid);
//        List<LoginLog> list = query.list();
        LoginLog loginLog = (LoginLog) query.uniqueResult();
        return loginLog;
    }

    @Override
    public List<LoginLog> getLoginLogByIp(String ip) {
        Query query = getSession().createQuery("from LoginLog where ip =?0");
        query.setParameter(0,ip);
        return query.list();
    }


}
