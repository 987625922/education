package com.project.gelingeducation.service;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.domain.Permission;
import com.project.gelingeducation.domain.Role;

import java.util.List;
import java.util.Set;

public interface IRoleService {

    Role findByRole(long id);

    void addRole(Role role);

    void addRole(Role role,long[] permissionIds);

    void addPermissionByIds(long roleId,long[] permissionIds);

    List<Role> list();

    void delRoleById(long id);

    PageResult getRolePageList(int currentPage, int pageSize);

    Role findDefault();

    List<Role> selByName(String name);

    void delMoreRolesByIds(long[] roleIds);

    List<Permission> getRoleByIdForPermission(long roleId);

    void updateRoleAndPermission(long id,String name,String remark,long[] permissionIds);
}
