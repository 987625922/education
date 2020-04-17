package com.project.gelingeducation.dao;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.domain.Permission;
import com.project.gelingeducation.domain.Role;

import java.util.List;
import java.util.Set;

public interface IRoleDao {

    void insert(Role role);

    Role findById(long id);

    List<Role> list();

    void delRoleById(long id);

    long getCount();

    PageResult getRolePageList(int currentPage, int pageSize);

    Role findDefault();

    List<Role> selByName(String name);

    void delByIds(long[] ids);

    List<Permission> getRoleByIdForPermission(long id);
}
