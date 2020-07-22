package com.project.gelingeducation.service;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.entity.Role;
import com.project.gelingeducation.entity.User;
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
//@ActiveProfiles("producation")
@WebAppConfiguration
@ContextConfiguration(locations = {"/spring/application-data.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class AdminServiceTest {

    @Autowired
    private IUserService userservice;

    @Autowired
    private IRoleService roleService;

    @Test
    public void insert() {
        User user = new User();
        user.setAccount("123456");
        user.setPassword("123456");
        userservice.addUser(user);
        userservice.addRole(user.getId(), 10L);

//        User user = new User();
//        user.setAccount("654321");
//        user.setPassword("654321");
//        userservice.addUser(user);
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
        PageResult bean = (PageResult) userservice.queryAll(1, 3);
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
        userservice.addRole(new Long(196), new Long(200));
    }

    @Test
    public void findRolesByUser() {
        Role role = userservice.findRoleByUserId(new Long(11));
        log.debug("==>" + role.getName());
    }
}
