package com.project.gelingeducation.service;

import com.project.gelingeducation.entity.Teacher;
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
 * @Description: teacher实体类的service测试类
 * @Date:Create：in 2020/6/28 16:46
 */
@Slf4j
@WebAppConfiguration
@ActiveProfiles("development")
@ContextConfiguration(locations = {"/spring/application-data.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TeacherServiceTest {

    /**
     * 教师实体类的service
     */
    @Autowired
    private ITeacherService teacherService;

    /**
     * 添加一个教师
     */
    @Test
    public void insert() {
        for (int i = 0; i < 10; i++) {
            Teacher teacher = new Teacher();
            teacher.setName("测试教师" + i);
            teacherService.save(teacher);
        }
    }

    /**
     * 测试
     * 根据id获取教师
     */
    @Test
    public void getById() {
        System.out.println(teacherService.getById(Long.valueOf(1)));
    }

    /**
     * 测试
     * 删除一个教师
     */
    @Test
    public void delete() {
        teacherService.delTeacher(4L);
    }

    /**
     * 测试
     * 删除多个教师
     */
    @Test
    public void delMore() {
        teacherService.delMore("16,17");
    }
}
