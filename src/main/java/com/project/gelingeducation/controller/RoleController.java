package com.project.gelingeducation.controller;

import com.project.gelingeducation.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @RequestMapping(value = "/lists",method = RequestMethod.POST)
    public Object lists(){
        return roleService.list();
    }

}
