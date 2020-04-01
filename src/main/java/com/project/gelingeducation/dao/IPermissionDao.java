package com.project.gelingeducation.dao;

import com.project.gelingeducation.domain.Permission;

public interface IPermissionDao {

    void insertPermission(Permission permission);

    Permission getById(long id);

}
