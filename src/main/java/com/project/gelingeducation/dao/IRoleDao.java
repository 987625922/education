package com.project.gelingeducation.dao;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.domain.Permission;
import com.project.gelingeducation.domain.Role;

import java.util.List;

public interface IRoleDao {

    void insert(Role role);

    Role findById(Long id);

    List<Role> list();

    void delRoleById(Long id);

    PageResult getRolePageList(Integer currentPage, Integer pageSize);

    Role findDefault();

    List selByName(String name);

    void delByIds(String ids);

    List<Permission> getRoleByIdForPermission(Long id);
}
