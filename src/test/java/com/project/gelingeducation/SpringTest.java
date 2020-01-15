package com.project.gelingeducation;

import com.project.gelingeducation.config.HibernateConfig;
import com.project.gelingeducation.domain.Subject;
import com.project.gelingeducation.domain.Video;
import com.project.gelingeducation.service.SubjectService;
import com.project.gelingeducation.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@Slf4j
@WebAppConfiguration
@ContextConfiguration(classes = HibernateConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringTest {

    @Autowired
    private VideoService videoService;

    @Autowired
    private SubjectService subjectService;

    @Test
    public void test() {
        List<Video> list = videoService.findAll();
        for (Video videoBean : list) {
            log.debug(videoBean.toString());
        }
    }

    @Test
    public void test1() {
//        Video video = new Video();
//        video.setName("测试的视频名字");
//        video.setVideoUrl("测试的视频链接");
//        videoService.insert(video);
        Subject subject = new Subject();
        subject.setName("测试的专栏");
        subjectService.insert(subject);
    }

    @Test
    public void test2() {
        Video videoBean = videoService.findById(1);
        log.debug(videoBean.toString());
    }

    @Test
    public void test3() {
        videoService.delectd(1);
    }

    @Test
    public void test4() {
        Video video = new Video();
        video.setId(2);
        video.setName("更新测试");
        videoService.updated(video);
    }

    @Test
    public void test5() {
//        log.debug(new Date().toString());
//        Integer i = null;
//        String s = null;
//        System.out.println(i);
//        System.out.println(s);

//        System.out.println(getMonthLastDay(2020, 1));
//        getCalendarData(2020, 1);
    }


}
