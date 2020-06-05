package com.project.gelingeducation.service;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.domain.Role;
import com.project.gelingeducation.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@Slf4j
@ActiveProfiles("development")
@WebAppConfiguration
@ContextConfiguration(locations = {"/spring/application-data.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class AdminServiceTest {

    @Autowired
    private IUserService userservice;

    @Test
    public void insert() {
        User user = new User();
        user.setAccount("123456");
        user.setPassword("123456");
        log.debug("findById获取的结果：" + userservice.addUser(user));

//        User user = new User();
//        user.setAccount("editor");
//        user.setPassword("editor");
//        log.debug("findById获取的结果：" + userservice.addUser(user));

//        for (int i = 0; i < 20; i++) {
//            User user = new User();
//            user.setAccount(String.valueOf(System.currentTimeMillis()).substring(5));
//            user.setPassword("123456");
//            userservice.addUser(user);
//        }
    }

    @Test
    public void findById() {
//        for (int i = 0; i < 10; i++) {
        log.debug("findById获取的结果：" + userservice.findById(new Long(1)));
//        }
    }

    //hibernate选择更新
    @Test
    public void update() {
        User user = new User();
        user.setId(new Long(1));
        user.setAccount("1234561");
        user.setPassword("123456");
        user.setCoverImg("图片1");
        userservice.update(user);
    }

    //hibernate选择更新
    @Test
    public void update1() {
        User user = new User();
        user.setId(new Long(1));
        user.setAccount("123456");
        user.setPassword("123456");
        user.setCoverImg("图片");
        userservice.update(user);
    }


    @Test
    public void lists() {
        PageResult bean = userservice.getLists(1, 3);
        List<User> users = (List<User>) bean.getLists();
        for (User user : users) {
            log.debug("==>  " + user);
        }
    }

    @Test
    public void findbyName() {
        User user = userservice.findUserByAccount("123456");
        log.debug("=====>" + user);
    }

    @Test
    public void addRoles() {
        userservice.addRole(new Long(7), new Long(15));
    }

    @Test
    public void findRolesByUser() {
        Role role = userservice.findRoleByUserId(new Long(11));
        log.debug("==>" + role.getName());
    }
}
