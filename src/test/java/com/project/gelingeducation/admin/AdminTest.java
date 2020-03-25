package com.project.gelingeducation.admin;

import com.project.gelingeducation.domain.User;
import com.project.gelingeducation.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@Slf4j
@WebAppConfiguration
@ContextConfiguration(classes = com.project.gelingeducation.common.config.HibernateConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class AdminTest {

    @Autowired
    private UserService service;

    @Test
    public void findDyPhone() {
        User user = new User();
        user.setAccount("1");
        user.setPassword("1");
        log.debug(service.register(user).toString());
    }

    @Test
    public void insert(){
        User user = new User();
        user.setAccount("1");
        user.setPassword("1");
        service.addUser(user);
    }

}
