package com.project.gelingeducation.service.Impl;

import com.project.gelingeducation.dao.IPermissionDao;
import com.project.gelingeducation.domain.Permission;
import com.project.gelingeducation.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private IPermissionDao permissionDao;

    @Override
    public void add(Permission permission) {
        permissionDao.insertPermission(permission);
    }

    @Override
    public Permission getById(long id) {
        return permissionDao.getById(id);
    }
}
