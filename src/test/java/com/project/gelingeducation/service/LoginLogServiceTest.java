package com.project.gelingeducation.service;

import com.project.gelingeducation.common.config.HibernateConfig;
import com.project.gelingeducation.domain.LoginLog;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

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

    @Autowired
    private LoginLogService loginLogService;

    @Test
    public void test() {
        LoginLog loginLog = new LoginLog();
        loginLog.setUid(1);
        loginLog.setLoginTime(new Date());
        loginLog.setBrowser("11");
        loginLog.setLocation("1");
        loginLog.setUserSystem("11");
        loginLogService.insert(loginLog);
    }

    @Test
    public void test1() {
        List<LoginLog> list = loginLogService.list();
        for (int i = 0; i < list.size(); i++) {
            log.debug("==>"+list.get(i).getLoginTime());
        }
    }
}
