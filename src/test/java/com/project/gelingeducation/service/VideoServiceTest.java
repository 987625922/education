package com.project.gelingeducation.service;

import com.project.gelingeducation.entity.Video;
import lombok.extern.slf4j.Slf4j;
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
     * 添加数据
     */
    @Test
    public void insert() {
        for (int i = 0; i < 10; i++) {
            Video video = new Video();
            video.setName("测试的视频").setCreateTime(new Date())
                    .setLastUpdateTime(new Date());
            videoService.insert(video);
        }
    }


}
