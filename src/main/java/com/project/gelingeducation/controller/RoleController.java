package com.project.gelingeducation.controller;

import com.project.gelingeducation.common.annotation.Log;
import com.project.gelingeducation.common.dto.JsonData;
import com.project.gelingeducation.entity.Role;
import com.project.gelingeducation.service.IRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

/**
 * @Author: LL
 * @Description: 用户权限角色的Controller
 */
@RestController
@RequestMapping("/api/role")
public class RoleController {

    /**
     * 角色实体类的service
     */
    @Autowired
    private IRoleService roleService;

    /**
     * 获取所有身份
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Log("获取所有身份")
    @RequestMapping(value = "/lists")
    public Object lists(@RequestParam(required = false) Integer currentPage,
                        @RequestParam(required = false) Integer pageSize) {
        return JsonData.buildSuccess(roleService.queryAll(currentPage, pageSize));
    }

    /**
     * 通过身份名获取身份
     *
     * @param name 用户名
     * @return
     * @throws UnsupportedEncodingException
     */
    @Log("通过名字获取身份")
    @RequestMapping(value = "/find_by_name")
    public Object findByName(String name) throws UnsupportedEncodingException {
        return JsonData.buildSuccess(roleService.selByName(name));
    }

    /**
     * 添加身份
     *
     * @param role
     * @return
     */
    @Log("添加身份")
    @RequestMapping(value = "/add")
    public Object addRole(@RequestBody Role role) {
        roleService.addRole(role);
        return JsonData.buildSuccess();
    }

    /**
     * 更新身份
     *
     * @param role
     * @return
     */
    @Log("更新身份")
    @RequestMapping(value = "/update")
    public Object update(@RequestBody Role role) {
        roleService.update(role);
        return JsonData.buildSuccess();
    }

    /**
     * 批量删除身份
     *
     * @param ids
     * @return
     */
    @Log("批量删除身份")
    @RequiresPermissions("user:root")
    @RequestMapping(value = "/batches_deletes")
    public Object delMore(String ids) {
        roleService.delMoreRolesByIds(ids);
        return JsonData.buildSuccess();
    }

    /**
     * 删除身份
     *
     * @param id
     * @return
     */
    @Log("删除身份")
    @RequiresPermissions("user:root")
    @RequestMapping(value = "/delete")
    public Object delete(Long id) {
        roleService.delRoleById(id);
        return JsonData.buildSuccess();
    }

    /**
     * 通过角色id获取身份的权限
     *
     * @param id 角色id
     * @return
     */
    @Log("获取身份的权限")
//    @RequiresPermissions("user:root")
    @RequestMapping(value = "/find_permission_by_role_id")
    public Object findPermissionById(Long id) {
        return JsonData.buildSuccess(roleService.getRoleByIdForPermission(id));
    }

    /**
     * 通过用户获取身份
     * @param userId 用户id
     * @return
     */
    @Log("通过用户获取身份")
    @RequestMapping("/find_by_user_id")
    public Object findRoleByUserId(Long userId) {
        return JsonData.buildSuccess(roleService.getRoleByUserId(userId));
    }
}
