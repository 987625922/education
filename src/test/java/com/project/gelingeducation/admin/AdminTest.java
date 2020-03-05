package com.project.gelingeducation.admin;

import com.project.gelingeducation.config.HibernateConfig;
import com.project.gelingeducation.dao.AdminInfoDao;
import com.project.gelingeducation.domain.AdminInfo;
import com.project.gelingeducation.domain.Subject;
import com.project.gelingeducation.domain.Video;
import com.project.gelingeducation.service.AdminInfoService;
import com.project.gelingeducation.service.SubjectService;
import com.project.gelingeducation.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Slf4j
@WebAppConfiguration
@ContextConfiguration(classes = HibernateConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class AdminTest {

    @Autowired
    private AdminInfoService service;

    @Test
    public void findDyPhone() {
        AdminInfo adminInfo = new AdminInfo();
        adminInfo.setAccount("1");
        adminInfo.setPassword("1");
        log.debug(service.register(adminInfo).toString());
    }

    @Test
    public void insert(){
        AdminInfo adminInfo = new AdminInfo();
        adminInfo.setAccount("1");
        adminInfo.setPassword("1");
        service.addUser(adminInfo);
    }

}
