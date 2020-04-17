package com.project.gelingeducation.controller;

import com.project.gelingeducation.common.dto.JsonData;
import com.project.gelingeducation.domain.Role;
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

    @RequestMapping(value = "/lists",method = RequestMethod.GET)
    public Object lists(){
        return JsonData.buildSuccess(roleService.list());
    }

    /**
     * 按名字搜索
     */
    @RequestMapping(value = "/sel_by_name", method = RequestMethod.POST)
    public Object selByName(String name) {
        return JsonData.buildSuccess(roleService.selByName(name));
    }

    /**
     * 添加身份
     *
     */
    @RequestMapping(value = "/add_role_and_permissionids", method = RequestMethod.POST)
    public Object selByName(String name,String remark,long[] permissionIds) {
        Role role = new Role();
        role.setName(name);
        role.setRemark(remark);
        roleService.addRole(role,permissionIds);
        return JsonData.buildSuccess();
    }


}
