package com.project.gelingeducation.controller;

import com.project.gelingeducation.common.dto.JsonData;
import com.project.gelingeducation.common.dto.WebIndex;
import com.project.gelingeducation.domain.LoginLog;
import com.project.gelingeducation.domain.WebDataBean;
import com.project.gelingeducation.service.IWebDataBeanService;
import com.project.gelingeducation.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    @Autowired
    private LoginLogService loginLogService;
    @Autowired
    private IWebDataBeanService webDataBeanService;

    /**
     * web端首页
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/web/index", method = RequestMethod.GET)
    public Object index(long id) {
        LoginLog loginLog = loginLogService.getByUserId(id);
        WebDataBean webDataBean = webDataBeanService.getWebDataBean();
        WebIndex webIndex = new WebIndex();
        webIndex.setLastLoginTime(loginLog.getLastLoginTime());
        webIndex.setAllLoginMun(webDataBean.getAllLoginMun());
        webIndex.setTodayLoginIpMun(webDataBean.getTodayLoginIpMun());
        webIndex.setTodayLoginMun(webDataBean.getTodayLoginMun());
        return JsonData.buildSuccess(webIndex);
    }

}
