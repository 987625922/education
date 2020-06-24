package com.project.gelingeducation.controller;

import com.project.gelingeducation.common.annotation.Log;
import com.project.gelingeducation.common.dto.JsonData;
import com.project.gelingeducation.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author LL
 */
@RestController
@RequestMapping("/api/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    @Log("获取所有权限")
    @RequestMapping(value = "/lists")
    public Object list(@RequestParam(required = false) Integer currentPage,
                       @RequestParam(required = false) Integer pageSize) {
        return JsonData.buildSuccess(permissionService.queryAll(currentPage, pageSize));
    }


}
