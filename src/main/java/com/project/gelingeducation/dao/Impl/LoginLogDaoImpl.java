package com.project.gelingeducation.dao.Impl;

import com.project.gelingeducation.dao.ILoginLogDao;
import com.project.gelingeducation.domain.LoginLog;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

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
        return getSession().createQuery(hql).getResultList();
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
