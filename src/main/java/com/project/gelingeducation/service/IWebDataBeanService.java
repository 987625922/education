package com.project.gelingeducation.service;

import com.project.gelingeducation.entity.User;
import com.project.gelingeducation.entity.WebDataBean;

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
     * 获取网页特定数据
     *
     * @return 网页特定数据实体类
     */
    WebDataBean getWebDataBean();

    /**
     * 添加登录数
     */
    void addLoginMun();

    /**
     * 清空今天的登录数
     */
    void clearTodayLoginMun();
}
