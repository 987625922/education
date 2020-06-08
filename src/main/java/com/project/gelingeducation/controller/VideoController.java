package com.project.gelingeducation.controller;

import com.project.gelingeducation.common.dto.JsonData;
import com.project.gelingeducation.domain.Video;
import com.project.gelingeducation.service.IVideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/video")
@RestController
@Slf4j
public class VideoController {

    @Autowired
    private IVideoService videoService;

    @RequestMapping(value = "/lists")
    public Object findAll(Integer currentPage, Integer pageSize) throws Exception {
        return JsonData.buildSuccess(videoService.list(currentPage, pageSize));
    }

    /**
     * {
     * "data":"{id:2}",
     * "sign":"1"
     * }
     *
     * @return
     */
    @RequestMapping(value = "/get_by_id")
    public Object findById(Long id) throws Exception {
        return videoService.findById(id);
    }
//    public Object findById(@RequestBody RequestBaseBean baseBean) throws Exception {
//        Video video = JsonUtils.GsonToBean(EncryptionUtils.encodeBASE64(baseBean.getData()), Video.class);
//        return CommonDEUtils.getEncodedPostString(JsonData.buildSuccess(videoService.findById(video.getId())));
//    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object add(Video video) {
        videoService.insert(video);
        return JsonData.buildSuccess();
    }

    @RequestMapping(value = "/delect", method = RequestMethod.POST)
    public Object delect(Long id) {
        videoService.delectd(id);
        return JsonData.buildSuccess();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object update(Video video) {
        videoService.updated(video);
        return JsonData.buildSuccess();
    }

}
