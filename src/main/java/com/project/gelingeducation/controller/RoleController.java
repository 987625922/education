package com.project.gelingeducation.controller;

import com.project.gelingeducation.common.annotation.Log;
import com.project.gelingeducation.common.dto.JsonData;
import com.project.gelingeducation.entity.Role;
import com.project.gelingeducation.service.IRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @Log("获取所有身份")
    @RequestMapping(value = "/lists")
    public Object lists(@RequestParam(required = false) Integer currentPage,
                        @RequestParam(required = false) Integer pageSize) {
        return JsonData.buildSuccess(roleService);
    }

    @Log("通过名字获取身份")
    @RequestMapping(value = "/find_by_name")
    public Object findByName(String name) {
        return JsonData.buildSuccess(roleService.selByName(name));
    }


    @Log("添加身份")
    @RequestMapping(value = "/add")
    public Object addRole(@RequestBody Role role) {
        roleService.addRole(role);
        return JsonData.buildSuccess();
    }

    @Log("更新身份")
    @RequestMapping(value = "/update")
    public Object update(@RequestBody Role role) {
        roleService.update(role);
        return JsonData.buildSuccess();
    }

    @Log("批量删除身份")
    @RequiresPermissions("user:root")
    @RequestMapping(value = "/batches_deletes")
    public Object delMore(String ids) {
        roleService.delMoreRolesByIds(ids);
        return JsonData.buildSuccess();
    }

    @Log("删除身份")
    @RequiresPermissions("user:root")
    @RequestMapping(value = "/delete")
    public Object delete(Long id) {
        roleService.delRoleById(id);
        return JsonData.buildSuccess();
    }

    @Log("获取身份的权限")
    @RequiresPermissions("user:root")
    @RequestMapping(value = "/find_permission_by_role_id")
    public Object findPermissionById(Long id) {
        return JsonData.buildSuccess(roleService.getRoleByIdForPermission(id));
    }

    @Log("通过用户获取身份")
    @RequestMapping("/find_by_user_id")
    public Object findRoleByUserId(Long userId){
        return JsonData.buildSuccess(roleService.getRoleByUserId(userId));
    }
}
