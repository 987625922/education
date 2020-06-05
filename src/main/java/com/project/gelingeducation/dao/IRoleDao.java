package com.project.gelingeducation.dao;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.domain.Permission;
import com.project.gelingeducation.domain.Role;

import java.util.List;
import java.util.Set;

public interface IRoleDao {

    void insert(Role role);

    Role findById(Long id);

    List<Role> list();

    void delRoleById(Long id);

    long getCount();

    PageResult getRolePageList(Integer currentPage, Integer pageSize);

    Role findDefault();

    List<Role> selByName(String name);

    void delByIds(Long[] ids);

    List<Permission> getRoleByIdForPermission(Long id);
}
