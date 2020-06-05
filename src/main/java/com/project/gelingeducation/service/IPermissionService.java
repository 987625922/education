package com.project.gelingeducation.service;

import com.project.gelingeducation.domain.Permission;

import java.util.List;

public interface IPermissionService {

    void add(Permission permission);

    Permission getById(Long id);

    List<Permission> list();

    List<Permission> getPermissionListByIds(Long[] ids);

}
