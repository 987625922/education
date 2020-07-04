package com.project.gelingeducation.service;

import com.project.gelingeducation.entity.LoginLog;
import com.project.gelingeducation.entity.User;

/**
 * @Author: LL
 * @Description: 登录日志的service接口
 * 备注：
 */
public interface ILoginLogService {

    /**
     * 添加登录日志
     * @param loginLog
     */
    void insert(LoginLog loginLog);

    /**
     * 搜索全部登录日志的实体类list
     *
     * @param currentPage 页码
     * @param pageSize    页数
     * @return 页码为空返回全都list，不为空返回分页实体类
     */
    Object queryAll(Integer currentPage,Integer pageSize);

    /**
     * 获取登录日志
     * @param uid 用户id
     * @return
     */
    LoginLog getByUserId(Long uid);

    /**
     * 添加或者更新用户登录日志实体类
     * 如果有了登录日志就更新，没有就插入
     * @param user 用户实体类
     */
    void saveOrUpdateLoginLogByUid(User user);

}
