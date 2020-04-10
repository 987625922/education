package com.project.gelingeducation.service;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.domain.Role;

import java.util.List;

public interface IRoleService {

    Role findByRole(long id);

    void add(Role role);

    void addPermissionByIds(long roleId,long[] permissionIds);

    List<Role> list();

    void delRoleById(long id);

    PageResult getRolePageList(int currentPage, int pageSize);

}
