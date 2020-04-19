package com.project.gelingeducation.controller;

import com.project.gelingeducation.common.dto.JsonData;
import com.project.gelingeducation.common.dto.WebIndex;
import com.project.gelingeducation.domain.LoginLog;
import com.project.gelingeducation.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    @Autowired
    private LoginLogService loginLogService;

    /**
     * web端首页
     * @param id
     * @return
     */
    @RequestMapping(value = "/web/index", method = RequestMethod.GET)
    public Object index(long id) {
        LoginLog loginLog = loginLogService.getByUserId(id);
        WebIndex webIndex = new WebIndex();
        webIndex.setLastLoginTime(loginLog.getLastLoginTime());
        return JsonData.buildSuccess(webIndex);
    }

}
