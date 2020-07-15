package com.project.gelingeducation.service;

/**
 * @Author: LL
 * @Description: 视频的Service
 */
public interface IWebDataBeanService {

    /**
     * 用户登录
     *
     * @param account  账号
     * @param password 密码
     * @return id 和 token
     */
    Object login(String account, String password);

    /**
     * 添加登录数
     */
    void addLoginMun();

    /**
     * 清空今天的登录数
     */
    void clearTodayLoginMun();
}
