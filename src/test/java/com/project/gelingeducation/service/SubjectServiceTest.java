package com.project.gelingeducation.service;

import com.project.gelingeducation.entity.Subject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @Author: LL
 * @Description: 专题的测试类
 * @Date:Create：in 2020/7/1 14:19
 */
@Slf4j
@WebAppConfiguration
@ActiveProfiles("development")
@ContextConfiguration(locations = {"/spring/application-data.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class SubjectServiceTest {

    /**
     * subject的实体类的service
     */
    @Autowired
    private ISubjectService subjectService;

    /**
     * 添加专题
     */
    @Test
    public void insert() {
        Subject subject = new Subject();
        subject.setName("测试的专题");
        subject.setBigImg("11111");
        subjectService.insert(subject);
    }


}
