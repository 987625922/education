package com.project.gelingeducation.service;

import com.project.gelingeducation.entity.Permission;
import com.project.gelingeducation.entity.Role;

import java.util.List;

public interface IRoleService {

    Role findByRole(Long id);

    void addRole(Role role);

    void addPermissionByIds(Long roleId,Long[] permissionIds);

    void update(Role role);

    void delRoleById(Long id);

    Object queryAll(Integer currentPage, Integer pageSize);

    Role findDefault();

    List<Role> selByName(String name);

    void delMoreRolesByIds(String roleIds);

    List<Permission> getRoleByIdForPermission(Long roleId);

    void updateRoleAndPermission(Long id,String name,String remark,
                                 Long[] permissionIds);

    Role getRoleByUserId(Long userId);
}
