package com.project.gelingeducation.service;

import com.project.gelingeducation.entity.Permission;

import java.util.List;

/**
 * @Author: LL
 * @Description: 权限的Service接口
 */
public interface IPermissionService {

    /**
     * 添加权限
     *
     * @param permission 权限实体类
     */
    void add(Permission permission);

    /**
     * 获取权限
     *
     * @param id 权限的id
     * @return
     */
    Permission getById(Long id);

    /**
     * 搜索全部权限的实体类list
     *
     * @param currentPage 页码
     * @param pageSize    页数
     * @return 页码为空返回全都list，不为空返回分页实体类
     */
    Object queryAll(Integer currentPage,Integer pageSize);

    /**
     * 根据id数据获取权限的list
     *
     * @param ids id数组
     * @return
     */
    List<Permission> getPermissionListByIds(Long[] ids);

}
