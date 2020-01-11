package com.project.gelingeducation.controller;

import com.project.gelingeducation.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class HomeController {

    @Autowired
    private VideoService videoService;

    @RequestMapping(value = "/test", method = GET)
    @ResponseBody
    public String home() {
        return videoService.findAll().toString();

    }

}
