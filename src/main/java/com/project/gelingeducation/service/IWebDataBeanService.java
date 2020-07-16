package com.project.gelingeducation.service;

import com.project.gelingeducation.common.dto.WebDataDto;
import com.project.gelingeducation.entity.User;

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
    void addLoginMun(User user);

    /**
     * 清空今天的登录数
     */
    void clearTodayLoginMun();

    /**
     * 获取首页的数据
     * @return
     */
    WebDataDto getWebData();
}
