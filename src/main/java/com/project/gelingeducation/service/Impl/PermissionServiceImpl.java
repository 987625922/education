package com.project.gelingeducation.service.Impl;

import com.project.gelingeducation.dao.IPermissionDao;
import com.project.gelingeducation.domain.Permission;
import com.project.gelingeducation.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private IPermissionDao permissionDao;

    @Transactional
    @Override
    public void add(Permission permission) {
        permissionDao.insertPermission(permission);
    }

    @Override
    public Permission getById(Long id) {
        return permissionDao.getById(id);
    }

    @Override
    public Object queryAll(Integer currentPage,Integer pageSize) {
        if (currentPage != null && pageSize != null){
            return permissionDao.queryAll(currentPage, pageSize);
        }else {
            return permissionDao.queryAll();
        }
    }

    @Override
    public List<Permission> getPermissionListByIds(Long[] ids) {
        return permissionDao.getPermissionListByIds(ids);
    }
}
