package com.project.gelingeducation.common.quartz;

import com.project.gelingeducation.service.IWebDataBeanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务
 * 0点清空当天登录人数
 */
//@Component
public class DealTodayLogJob {

    @Autowired
    private IWebDataBeanService webDataBeanService;

    @Scheduled(cron = "0 0 0 0 0 ?")
    public void dealLoginStatus() {
        webDataBeanService.clearTodayLoginMun();
    }
}
