package com.project.gelingeducation.dao;

import com.project.gelingeducation.domain.Permission;

import java.util.List;

public interface IPermissionDao {

    void insertPermission(Permission permission);

    Permission getById(long id);

    List<Permission> list();
}
