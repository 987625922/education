package com.project.gelingeducation.service;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.entity.Course;
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
public class CourseServiceTest {

    @Autowired
    private ICourseService courseService;

    @Test
    public void insert() {
        String[] names = {"同步教材：人教版高中数学必修三", "左宗棠-不惧官场潜规则", "同步教材：人教版高中数学必修四"
                , "同步教材：人教版高中英语必修一", "同步教材：人教版高中英语必修二", "同步教材：人教版高中英语必修四"};
        for (int i = 0; i < names.length; i++) {
            Course course = new Course().setName(names[i]).setPrice(1.0);
            courseService.insert(course);
        }
    }

    @Test
    public void pageList() {
        PageResult pageResult = (PageResult) courseService.queryAll(1, 10);
        log.debug("==>" + pageResult.toString());
    }


    @Test
    public void selByNameOrStatusOrPriceOrTeacher() {
        PageResult pageResult = (PageResult) courseService.selByNameOrStatusOrPriceOrTeacher(null, -1,
                new Double(-1), new Double(-1), new Long(1),
                new Integer(1), new Integer(10));
        System.out.println();
    }

    @Test
    public void delect() {
        courseService.delect(7L);
    }
}
