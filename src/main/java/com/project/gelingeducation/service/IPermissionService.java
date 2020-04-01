package com.project.gelingeducation.service;

import com.project.gelingeducation.domain.Permission;

public interface IPermissionService {

    void add(Permission permission);

    Permission getById(long id);

}
