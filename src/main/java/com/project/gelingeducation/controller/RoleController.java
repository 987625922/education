package com.project.gelingeducation.controller;

import com.project.gelingeducation.common.dto.JsonData;
import com.project.gelingeducation.domain.Role;
import com.project.gelingeducation.service.IRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @RequestMapping(value = "/lists", method = RequestMethod.GET)
    public Object lists() {
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
     */
    @RequestMapping(value = "/add_role_and_permissionids", method = RequestMethod.POST)
    public Object selByName(String name, String remark, long[] permissionIds) {
        Role role = new Role();
        role.setName(name);
        role.setRemark(remark);
        roleService.addRole(role, permissionIds);
        return JsonData.buildSuccess();
    }

    /**
     * 批量删除客户
     */
    @RequiresPermissions("user:root")
    @RequestMapping(value = "/batches_deletes", method = RequestMethod.POST)
    public Object delMoreUser(long[] roleIds) {
        roleService.delMoreRolesByIds(roleIds);
        return JsonData.buildSuccess();
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @RequiresPermissions("user:root")
    @RequestMapping(value = "/del_role", method = RequestMethod.POST)
    public Object deluser(long id) {
        roleService.delRoleById(id);
        return JsonData.buildSuccess();
    }

    /**
     * 获取身份的权限
     *
     * @param id
     * @return
     */
    @RequiresPermissions("user:root")
    @RequestMapping(value = "/get_role_by_id_for_permission", method = RequestMethod.POST)
    public Object getRoleByIdForPermission(long id) {
        return JsonData.buildSuccess(roleService.getRoleByIdForPermission(id));
    }

    /**
     * 更新身份
     *
     * @param id
     * @return
     */
    @RequiresPermissions("user:root")
    @RequestMapping(value = "/update_role_and_permission", method = RequestMethod.POST)
    public Object updateRoleAndPermission(long id, String name, String remark, long[] permissionIds) {
        roleService.updateRoleAndPermission(id, name, remark, permissionIds);
        return JsonData.buildSuccess();
    }
}
