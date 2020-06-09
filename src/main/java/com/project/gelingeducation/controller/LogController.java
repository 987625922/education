package com.project.gelingeducation.controller;

import com.project.gelingeducation.common.annotation.Log;
import com.project.gelingeducation.common.dto.JsonData;
import com.project.gelingeducation.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/logs")
@RestController
public class LogController {

    @Autowired
    private ILogService logService;

    @Log("获取所有日志")
    @RequestMapping(value = "/lists")
    public Object queryAll(@RequestParam(required = false) Integer currentPage,
                           @RequestParam(required = false) Integer pageSize) {
        return JsonData.buildSuccess(logService.queryAll(currentPage, pageSize));
    }

    @Log("删除所有info日志")
    @GetMapping("/delete_info_log")
    public void deleteInfoLog() {
        logService.delAllByInfo();
    }

    @Log("删除所有error日志")
    @GetMapping("/delete_error_log")
    public void deleteErrorLog() {
        logService.delAllByError();
    }
}
