package com.project.gelingeducation.service;

import com.project.gelingeducation.domain.Role;

import java.util.List;

public interface IRoleService {

    Role findByRole(long id);

    void add(Role role);

    void addPermissionByIds(long roleId,long[] permissionIds);

    List<Role> list();

}
