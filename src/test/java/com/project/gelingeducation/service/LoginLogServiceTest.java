package com.project.gelingeducation.service;

import com.project.gelingeducation.config.HibernateConfig;
import com.project.gelingeducation.domain.AdminInfo;
import com.project.gelingeducation.domain.LoginLog;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.awt.*;
import java.util.Date;
import java.util.List;

@Slf4j
@WebAppConfiguration
@ContextConfiguration(classes = HibernateConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class LoginLogServiceTest {
    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Test
    @Transactional
    public void test() {
        Session session = getSession();
        LoginLog loginLog = new LoginLog();
        loginLog.setId(2);
        loginLog.setLoginTime(new Date());
        session.save(loginLog);
    }

    @Test
    @Transactional
    public void test1() {
        Session session = getSession();
        String hql = "from LoginLog";
        TypedQuery<LoginLog> query = session.createQuery(hql);
        List<LoginLog> list = query.getResultList();
        for (int i = 0; i < list.size(); i++) {
            log.debug("==>"+list.get(i).getLoginTime());
        }
    }
}
