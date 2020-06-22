package com.project.gelingeducation.service;

import com.project.gelingeducation.entity.Permission;

import java.util.List;

public interface IPermissionService {

    void add(Permission permission);

    Permission getById(Long id);

    Object queryAll(Integer currentPage,Integer pageSize);

    List<Permission> getPermissionListByIds(Long[] ids);

}
