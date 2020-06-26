package com.project.gelingeducation.service;

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
public class LogServiceTest {

    @Autowired
    private ILogService logService;

    @Test
    public void queryAll() {
        logService.queryAll(null,null);
    }

}
