package com.project.gelingeducation.controller;

import com.project.gelingeducation.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/logs")
@RestController
public class LogController {

    @Autowired
    private ILogService logService;

    @RequestMapping("/list")
    public Object queryAll(int currentPage, int pageSize) {
        return logService.queryAll(currentPage, pageSize);
    }



}
