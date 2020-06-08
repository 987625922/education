package com.project.gelingeducation.controller;

import com.project.gelingeducation.common.dto.JsonData;
import com.project.gelingeducation.domain.Role;
import com.project.gelingeducation.service.IRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @RequestMapping(value = "/lists")
    public Object lists() {
        return JsonData.buildSuccess(roleService.list());
    }

    /**
     * 按名字搜索
     */
    @RequestMapping(value = "/sel_by_name")
    public Object selByName(String name) {
        return JsonData.buildSuccess(roleService.selByName(name));
    }

    /**
     * 添加身份
     */
    @RequestMapping(value = "/add")
    public Object selByName(@RequestBody Role role) {
        roleService.addRole(role);
        return JsonData.buildSuccess();
    }

    /**
     * 批量删除客户
     */
    @RequiresPermissions("user:root")
    @RequestMapping(value = "/batches_deletes")
    public Object delMoreUser(String ids) {
        roleService.delMoreRolesByIds(ids);
        return JsonData.buildSuccess();
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @RequiresPermissions("user:root")
    @RequestMapping(value = "/del_role")
    public Object deluser(Long id) {
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
    @RequestMapping(value = "/get_role_by_id_for_permission")
    public Object getRoleByIdForPermission(Long id) {
        return JsonData.buildSuccess(roleService.getRoleByIdForPermission(id));
    }

    /**
     * 更新身份
     *
     * @param id
     * @return
     */
    @RequiresPermissions("user:root")
    @RequestMapping(value = "/update_role_and_permission")
    public Object updateRoleAndPermission(Long id, String name, String remark,
                                          Long[] permissionIds) {
        roleService.updateRoleAndPermission(id, name, remark, permissionIds);
        return JsonData.buildSuccess();
    }

    @RequestMapping("/get_role_by_user_id")
    public Object getRoleByUserId(Long userId){
        return JsonData.buildSuccess(roleService.getRoleByUserId(userId));
    }
}
