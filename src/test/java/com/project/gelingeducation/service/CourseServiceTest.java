package com.project.gelingeducation.service;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.domain.Course;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;

@Slf4j
@WebAppConfiguration
@ContextConfiguration(locations = {"/spring/application-data.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class CourseServiceTest {

    @Autowired
    private ICourseService courseService;

    @Test
    public void insert() {
        String[] names = {"同步教材：人教版高中数学必修三", "左宗棠-不惧官场潜规则", "同步教材：人教版高中数学必修四"
                , "同步教材：人教版高中英语必修一", "同步教材：人教版高中英语必修二", "同步教材：人教版高中英语必修四"};
        for (int i = 0; i < names.length; i++) {
            Course course = new Course();
            course.setName(names[i]);
            course.setModifyTime(new Date());
            course.setPrice(1);
            courseService.insert(course);
        }
    }

    @Test
    public void pageList() {
        PageResult pageResult = courseService.getLists(1, 10);
        log.debug("==>" + pageResult.toString());
    }
}
