package com.project.gelingeducation.service;

import com.project.gelingeducation.domain.Teacher;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@Slf4j
@WebAppConfiguration
@ActiveProfiles("development")
@ContextConfiguration(locations = {"/spring/application-data.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TeacherServiceTest {

    @Autowired
    private ITeacherService teacherService;

    @Test
    public void insert() {
        for (int i = 0; i < 10; i++) {
            Teacher teacher = new Teacher();
            teacher.setName("测试教师" + i);
            teacherService.addTeacher(teacher);
        }
    }

    @Test
    public void getById(){
        System.out.println(teacherService.getById(1));
    }

}
