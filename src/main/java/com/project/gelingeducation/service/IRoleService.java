package com.project.gelingeducation.service;

import com.project.gelingeducation.entity.Permission;
import com.project.gelingeducation.entity.Role;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @Author: LL
 * @Description: 教师的Service接口
 */
public interface IRoleService {

    /**
     * 获取角色实体类
     *
     * @param id 角色id
     * @return
     */
    Role findByRole(Long id);

    /**
     * 添加角色
     *
     * @param role 角色实体类
     */
    void addRole(Role role);

    /**
     * 根据角色id添加权限
     *
     * @param roleId        角色id
     * @param permissionIds 权限id
     */
    void addPermissionByIds(Long roleId,Long[] permissionIds);

    /**
     * 添加角色
     *
     * @param role 角色实体类
     */
    void update(Role role);

    /**
     * 删除角色
     *
     * @param id 角色id
     */
    void delRoleById(Long id);

    /**
     * 搜索全部角色的实体类list
     *
     * @param currentPage 页码
     * @param pageSize    页数
     * @return 页码为空返回全都list，不为空返回分页实体类
     */
    Object queryAll(Integer currentPage, Integer pageSize);

    /**
     * 搜索创建账号默认的角色
     *
     * @return
     */
    Role findDefault();

    /**
     * 根据匹配角色名搜索角色list
     *
     * @param name 匹配的角色名
     * @return
     */
    List<Role> selByName(String name) throws UnsupportedEncodingException;

    /**
     * 根据id字符串删除角色
     *
     * @param roleIds 1,2,3 id字符串
     */
    void delMoreRolesByIds(String roleIds);

    /**
     * 获取角色的权限
     *
     * @param roleId
     * @return
     */
    List<Permission> getRoleByIdForPermission(Long roleId);

    /**
     * 更新角色和权限
     *
     * @param id            角色id
     * @param name          角色名
     * @param remark        角色备注
     * @param permissionIds 1,2,3 权限id字符串
     */
    void updateRoleAndPermission(Long id,String name,String remark,
                                 Long[] permissionIds);
    /**
     * 根据用户id获取角色
     *
     * @param userId 用户id
     * @return 角色id
     */
    Role getRoleByUserId(Long userId);
}
