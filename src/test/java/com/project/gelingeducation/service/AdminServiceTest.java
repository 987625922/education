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
    private IUserService IUserService;

    @Test
    public void login() {
        User user = new User();
        user.setAccount("123456");
        user.setPassword("123456");
        IUserService.login(user);
    }

    @Test
    public void insert() {
        User user = new User();
        user.setAccount("123456");
        user.setPassword("123456");
        log.debug("findById获取的结果：" + IUserService.addUser(user));
    }

    @Test
    public void findById() {
//        for (int i = 0; i < 10; i++) {
        log.debug("findById获取的结果：" + IUserService.findById(1));
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
        IUserService.update(user);
    }

    //hibernate选择更新
    @Test
    public void update1() {
        User user = new User();
        user.setId(1);
        user.setAccount("123456");
        user.setPassword("123456");
        user.setCoverImg("图片");
        IUserService.update(user);
    }


    @Test
    public void lists() {
        PageResult bean = IUserService.getLists(3, 3);
        List<User> users = (List<User>) bean.getLists();
        for (User user : users) {
            log.debug("==>  " + user);
        }
    }


}
