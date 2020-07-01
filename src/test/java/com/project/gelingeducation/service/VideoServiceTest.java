package com.project.gelingeducation.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.gelingeducation.common.utils.JsonUtil;
import com.project.gelingeducation.entity.Course;
import com.project.gelingeducation.entity.Teacher;
import com.project.gelingeducation.entity.Video;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;

/**
 * @Author: LL
 * @Description: video实体类的service测试类
 * @Date:Create：in 2020/6/28 16:46
 */
@Slf4j
@WebAppConfiguration
@ActiveProfiles("development")
@ContextConfiguration(locations = {"/spring/application-data.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class VideoServiceTest {

    /**
     * video实体类的service
     */
    @Autowired
    private IVideoService videoService;
    /**
     * 老师实体类的service
     */
    @Autowired
    private ITeacherService teacherService;
    /**
     * 课程实体类的service
     */
    @Autowired
    private ICourseService courseService;

    /**
     * 添加数据
     */
    @Test
    public void insert() {
//        for (int i = 0; i < 10; i++) {
        Video video = new Video();
        video.setName("测试的视频").setCreateTime(new Date())
                .setLastUpdateTime(new Date());
        videoService.insert(video);
//        }
    }

    /**
     * 条件搜索
     */
    @Test
    public void searchCriteria() throws JsonProcessingException {
        log.info("条件搜索结果：   "
                + JsonUtil.jsonToString(videoService.searchByCriteria("1",
                "测试的视频", "1")));
    }

    /**
     * 视频添加老师
     */
    @Test
    public void addVideoTeacher() {
        Video video = videoService.findById(1L);
        Teacher teacher = teacherService.getById(3L);
        video.setTeacher(teacher);
        videoService.updated(video);
    }

    /**
     * 视频添加课程
     */
    @Test
    public void addVideoCourse() {
        Video video = videoService.findById(1L);
//        Course course = courseService.findById(1L);
        Course course = new Course();
        course.setId(1L);
        //todo 级联更新失败
//        video.getCourses().add(course);
        course.getVideos().add(video);
//        videoService.updated(video);
        courseService.update(course);
    }

    /**
     * hibernate的session工厂
     */
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * 获取线程上ThreadLocal的hibernate session
     * @return hibernate的session
     */
    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

}
