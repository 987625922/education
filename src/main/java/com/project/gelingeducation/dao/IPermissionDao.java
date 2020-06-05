package com.project.gelingeducation.dao;

import com.project.gelingeducation.domain.Permission;

import java.util.List;

public interface IPermissionDao {

    void insertPermission(Permission permission);

    Permission getById(Long id);

    List<Permission> list();

    List<Permission> getPermissionListByIds(Long[] ids);
}
