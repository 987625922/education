package com.project.gelingeducation.service;

import com.project.gelingeducation.common.config.HibernateConfig;
import com.project.gelingeducation.domain.User;
import com.project.gelingeducation.dto.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@Slf4j
@WebAppConfiguration
@ContextConfiguration(classes = HibernateConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class AdminServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void login() {
        User user = new User();
        user.setAccount("123456");
        user.setPassword("123456");
        userService.login(user);
    }

    @Test
    public void insert() {
        User user = new User();
        user.setAccount("123456");
        user.setPassword("123456");
        log.debug("findById获取的结果：" + userService.addUser(user));
    }

    @Test
    public void findById() {
//        for (int i = 0; i < 10; i++) {
        log.debug("findById获取的结果：" + userService.findById(1));
//        }
    }

    //hibernate选择更新
    @Test
    public void update() {
        User user = new User();
        user.setId(1);
        user.setAccount("1234561");
        user.setPassword("123456");
        user.setCoverImg("图片1");
        userService.update(user);
    }

    //hibernate选择更新
    @Test
    public void update1() {
        User user = new User();
        user.setId(1);
        user.setAccount("123456");
        user.setPassword("123456");
        user.setCoverImg("图片");
        userService.update(user);
    }


    @Test
    public void lists() {
        PageResult bean = userService.getLists(3, 3);
        List<User> users = (List<User>) bean.getLists();
        for (User user : users) {
            log.debug("==>  " + user);
        }
    }


}
