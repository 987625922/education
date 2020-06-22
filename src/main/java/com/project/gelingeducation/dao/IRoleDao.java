package com.project.gelingeducation.dao;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.entity.Permission;
import com.project.gelingeducation.entity.Role;

import java.util.List;

public interface IRoleDao {

    void insert(Role role);

    Role findById(Long id);

    List<Role> queryAll();

    void delRoleById(Long id);

    PageResult queryAll(Integer currentPage, Integer pageSize);

    Role findDefault();

    List selByName(String name);

    void delByIds(String ids);

    List<Permission> getRoleByIdForPermission(Long id);

    Role getRoleByUserId(Long userId);

    void update(Role role);

}
