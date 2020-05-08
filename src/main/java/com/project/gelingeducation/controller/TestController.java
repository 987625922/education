package com.project.gelingeducation.controller;

import com.project.gelingeducation.common.exception.AllException;
import com.project.gelingeducation.service.IVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private IVideoService videoService;

    @RequestMapping(value = "/test", method = GET)
    @ResponseBody
    public String home() {
        int i = 1/0;
        throw new AllException(-101,"异常输出测试");
//        return videoService.findAll().toString();
    }

    /**
     * Path参数
     * http://localhost:8080/params/get5/bookname/info
     *
     * @param userName
     * @return
     */
    @RequestMapping(path = "/get5/{userName}/info", method = RequestMethod.GET)
    @ResponseBody
    public Object req6(@PathVariable(name = "userName") String userName) {
        return "输入的数据为："+userName;
//        return JsonData.buildSuccess(userName);
    }

}
