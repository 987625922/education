package com.project.gelingeducation.service.impl;

import com.project.gelingeducation.dao.IPermissionDao;
import com.project.gelingeducation.entity.Permission;
import com.project.gelingeducation.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: LL
 * @Description: 权限的Service
 */
@Service
@Transactional(readOnly = true)
public class PermissionServiceImpl implements IPermissionService {

    /**
     * 权限实体类的dao
     */
    @Autowired
    private IPermissionDao permissionDao;

    /**
     * 添加权限
     *
     * @param permission 权限实体类
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(Permission permission) {
        permissionDao.insertPermission(permission);
    }

    /**
     * 获取权限
     *
     * @param id 权限的id
     * @return
     */
    @Override
    public Permission getById(Long id) {
        return permissionDao.getById(id);
    }

    /**
     * 搜索全部权限的实体类list
     *
     * @param currentPage 页码
     * @param pageSize    页数
     * @return 页码为空返回全都list，不为空返回分页实体类
     */
    @Override
    public Object queryAll(Integer currentPage, Integer pageSize) {
        if (currentPage != null && pageSize != null) {
            return permissionDao.queryAll(currentPage, pageSize);
        } else {
            return permissionDao.queryAll();
        }
    }

    /**
     * 根据id数据获取权限的list
     *
     * @param ids id数组
     * @return
     */
    @Override
    public List<Permission> getPermissionListByIds(Long[] ids) {
        return permissionDao.getPermissionListByIds(ids);
    }
}
