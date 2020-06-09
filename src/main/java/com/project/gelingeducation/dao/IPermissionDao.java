package com.project.gelingeducation.dao;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.domain.Permission;

import java.util.List;

public interface IPermissionDao {

    void insertPermission(Permission permission);

    Permission getById(Long id);

    List queryAll();

    PageResult queryAll(Integer currentPage,Integer pageSize);

    List<Permission> getPermissionListByIds(Long[] ids);
}
