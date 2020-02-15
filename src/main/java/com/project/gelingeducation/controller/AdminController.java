package com.project.gelingeducation.controller;

import com.project.gelingeducation.domain.AdminInfo;
import com.project.gelingeducation.domain.JsonData;
import com.project.gelingeducation.service.AdminInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 管理员controller
 */
//@CrossOrigin(origins = {"/"}, maxAge = 72000L)
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminInfoService adminInfoService;

    /**
     * 注册接口
     * @param adminInfo
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Object register(@RequestBody AdminInfo adminInfo) {
        return JsonData.buildSuccess(adminInfoService.register(adminInfo));
    }

    /**
     * 登录接口
     * @param adminInfo
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object login(@RequestBody AdminInfo adminInfo) {
        return JsonData.buildSuccess(adminInfoService.login(adminInfo));
    }




}
