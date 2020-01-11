package com.project.gelingeducation.controller;

import com.project.gelingeducation.domain.JsonData;
import com.project.gelingeducation.domain.RequestBaseBean;
import com.project.gelingeducation.domain.Video;
import com.project.gelingeducation.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/video")
@RestController
@Slf4j
public class VideoController {

    @Autowired
    private VideoService videoService;

    @RequestMapping(value = "/findall")
    public Object findAll() {
        return JsonData.buildSuccess(videoService.findAll());
    }

    /**
     * {
     * "data":"{id:2}",
     * "sign":"1"
     * }
     *
     * @param baseBean
     * @return
     */
    @RequestMapping(value = "/findbyid", method = RequestMethod.POST)
    public Object findById(@RequestBody RequestBaseBean baseBean) {
        log.debug(baseBean.toString());
        return JsonData.buildSuccess(videoService.findById(2));
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object add(Video video) {
        videoService.insert(video);
        return JsonData.buildSuccess();
    }

    @RequestMapping(value = "/delect", method = RequestMethod.POST)
    public Object delect(long id) {
        videoService.delectd(id);
        return JsonData.buildSuccess();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object update(Video video) {
        videoService.updated(video);
        return JsonData.buildSuccess();
    }

}
