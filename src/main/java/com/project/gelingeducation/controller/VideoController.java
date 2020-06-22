package com.project.gelingeducation.controller;

import com.project.gelingeducation.common.annotation.Log;
import com.project.gelingeducation.common.dto.JsonData;
import com.project.gelingeducation.entity.Video;
import com.project.gelingeducation.service.IVideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/video")
@RestController
@Slf4j
public class VideoController {

    @Autowired
    private IVideoService videoService;

    @Log("获取视频列表")
    @RequestMapping(value = "/lists")
    public Object queryAll(@RequestParam(required = false) Integer currentPage,
                          @RequestParam(required = false) Integer pageSize) throws Exception {
        return JsonData.buildSuccess(videoService.queryAll(currentPage, pageSize));
    }

    @Log("通过id获取视频")
    @RequestMapping(value = "/find_by_id")
    public Object findById(Long id) throws Exception {
        return videoService.findById(id);
    }

    @Log("添加视频")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object add(Video video) {
        videoService.insert(video);
        return JsonData.buildSuccess();
    }

    @Log("删除视频")
    @RequestMapping(value = "/delete")
    public Object delete(Long id) {
        videoService.delectd(id);
        return JsonData.buildSuccess();
    }

    @Log("更新视频")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object update(Video video) {
        videoService.updated(video);
        return JsonData.buildSuccess();
    }

}
