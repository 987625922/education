package com.project.gelingeducation.service;

import com.project.gelingeducation.entity.User;

/**
 * @Author: LL
 * @Description: 视频的Service
 */
public interface IWebDataBeanService {

    /**
     * 用户登录
     *
     * @param user 用户实体类
     * @return id 和 token
     */
    Object login(User user);

    /**
     * 添加登录数
     */
    void addLoginMun();

    /**
     * 清空今天的登录数
     */
    void clearTodayLoginMun();
}
