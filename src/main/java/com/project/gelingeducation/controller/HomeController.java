package com.project.gelingeducation.controller;

import com.project.gelingeducation.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    /**
     * Path参数
     * http://localhost:8080/params/get5/bookname/info
     *
     * @param userName
     * @return
     */
    @RequestMapping(path = "get5/{userName}/info", method = RequestMethod.GET)
    @ResponseBody
    public Object req6(@PathVariable(name = "userName") String userName) {
        return "输入的数据为："+userName;
//        return JsonData.buildSuccess(userName);
    }

}
