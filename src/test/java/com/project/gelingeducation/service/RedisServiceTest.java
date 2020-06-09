package com.project.gelingeducation.service;

import com.project.gelingeducation.common.redis.JedisCacheClient;
import com.project.gelingeducation.common.redis.servicr.IRedisCacheService;
import com.project.gelingeducation.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@Slf4j
@ActiveProfiles("development")
@WebAppConfiguration
@ContextConfiguration(locations = {"/spring/application-data.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class RedisServiceTest {

    @Autowired
    IRedisCacheService redisCacheService;

    @Autowired
    JedisCacheClient jedisCacheClient;

    @Test
    public void test() throws Exception {
//        User user = new User();
//        user.setUserName("11");
//        redisCacheService.saveUser(user);
        jedisCacheClient.set("11","11");
    }
}
