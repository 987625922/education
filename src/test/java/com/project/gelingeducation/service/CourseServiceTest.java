package com.project.gelingeducation.service;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.entity.Course;
import com.project.gelingeducation.entity.Subject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

/**
 * @Author: LL
 * @Description: 课程的测试类
 * @Date:Create：in 2020/7/1
 */
@Slf4j
@WebAppConfiguration
@ActiveProfiles("development")
@ContextConfiguration(locations = {"/spring/application-data.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class CourseServiceTest {

    /**
     * 课程的service
     */
    @Autowired
    private ICourseService courseService;

    /**
     * 专题的service
     */
    @Autowired
    private ISubjectService subjectService;

    /**
     * 添加课程
     */
    @Test
    public void insert() {
        String[] names = {"同步教材：人教版高中数学必修三", "左宗棠-不惧官场潜规则", "同步教材：人教版高中数学必修四"
                , "同步教材：人教版高中英语必修一", "同步教材：人教版高中英语必修二", "同步教材：人教版高中英语必修四"};
        for (int i = 0; i < names.length; i++) {
            Course course = new Course().setName(names[i]).setPrice(1.0);
            courseService.insert(course);
        }
    }

    /**
     * 获取课程的分页类
     */
    @Test
    public void pageList() {
        PageResult pageResult = (PageResult) courseService.queryAll(1, 10);
        log.info("==>" + pageResult.toString());
    }

    /**
     * 按条件搜索
     */
    @Test
    public void selByNameOrStatusOrPriceOrTeacher() throws UnsupportedEncodingException {
        PageResult pageResult = (PageResult) courseService.selByNameOrStatusOrPriceOrTeacher(null, -1,
                new Double(-1), new Double(-1), new Long(1),
                new Integer(1), new Integer(10));
        System.out.println();
    }

    /**
     * 删除课程
     */
    @Test
    public void delect() {
        courseService.delect(7L);
    }

    /**
     * 课程添加专题
     */
    @Test
    public void courseAddSubject() {
//        Subject subject = subjectService.findById(1L);
//        Course course = courseService.findById(1L);
//        subject.getCourses().add(course);
        Course course = new Course();
        course.setId(1L);
        course.setName("11111");
        course.setLastUpdateTime(new Date());
        Subject subject = new Subject();
        subject.setId(1L);
//        subject.getCourses().add(course);
        course.getSubjects().add(subject);
//        subjectService.updated(subject);
        courseService.update(course);
    }

    /**
     * 通过专题id获取课程列表
     */
    @Test
    public void getCourseBySubjectId() {
        List list = (List) courseService.getCourseListBySubjectId(5L);
        log.info("getCourseBySubjectId " + list.size());
        list.forEach(o ->
                log.info("getCourseBySubjectId " + o)
        );
    }
}
