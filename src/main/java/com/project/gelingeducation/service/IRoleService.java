package com.project.gelingeducation.service;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.domain.Permission;
import com.project.gelingeducation.domain.Role;

import java.util.List;
import java.util.Set;

public interface IRoleService {

    Role findByRole(Long id);

    void addRole(Role role);

    void addPermissionByIds(Long roleId,Long[] permissionIds);

    List<Role> list();

    void delRoleById(Long id);

    PageResult getRolePageList(Integer currentPage, Integer pageSize);

    Role findDefault();

    List<Role> selByName(String name);

    void delMoreRolesByIds(String roleIds);

    List<Permission> getRoleByIdForPermission(Long roleId);

    void updateRoleAndPermission(Long id,String name,String remark,
                                 Long[] permissionIds);
}
