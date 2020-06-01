package com.project.gelingeducation.controller;

import com.project.gelingeducation.common.dto.JsonData;
import com.project.gelingeducation.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/logs")
@RestController
public class LogController {

    @Autowired
    private ILogService logService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Object queryAll(int currentPage, int pageSize) {
        return JsonData.buildSuccess(logService.queryAll(currentPage, pageSize));
    }

    @GetMapping("/delete_info_log")
    public void deleteInfoLog() {
        logService.delAllByInfo();
    }

    @GetMapping("/delete_error_log")
    public void deleteErrorLog() {
        logService.delAllByError();
    }
}
