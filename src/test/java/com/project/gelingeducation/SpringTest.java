package com.project.gelingeducation;

import com.project.gelingeducation.domain.Video;
import com.project.gelingeducation.service.IUserService;
import com.project.gelingeducation.service.ISubjectService;
import com.project.gelingeducation.service.IVideoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Slf4j
@WebAppConfiguration
@ActiveProfiles("development")
@ContextConfiguration(locations = {"/spring/application-data.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringTest {

    @Autowired
    private IVideoService videoService;

    @Autowired
    private ISubjectService subjectService;

    @Autowired
    private IUserService service;

    @Test
    public void test() {
//        List<Video> list = videoService.list(1,10);
//        for (Video videoBean : list) {
//            log.debug(videoBean.toString());
//        }
    }

    @Test
    public void test1() {
        Video video = new Video();
        video.setName("测试的视频名字");
        video.setVideoUrl("测试的视频链接");
        video.setCreateTime(new Date());
        video.setLastUpdateTime(new Date());
        videoService.insert(video);
//        Subject subject = new Subject();
//        subject.setName("测试的专栏");
//        subjectService.insert(subject);

    }

    @Test
    public void test2() {
        Video videoBean = videoService.findById(new Long(1));
        log.debug(videoBean.toString());
    }

    @Test
    public void test3() {
        videoService.delectd(new Long(1));
    }

    @Test
    public void test4() {
        Video video = new Video();
        video.setId(new Long(2));
        video.setName("更新测试");
        videoService.updated(video);
    }

    @Test
    public void test5() {
        System.out.println("===>> " + service.queryAll(0, 3));
    }


    public static Calendar getLastCalendar(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, -1);
        return calendar;
    }


}
