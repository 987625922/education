package com.project.gelingeducation.common.utils;

import com.project.gelingeducation.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

/**
 * @Author: LL
 * @Description:shiro工具类
 * @Date:Create：in 2020/7/16 14:44
 */
public class ShiroUtil {
    private ShiroUtil() {
    }

    /**
     * 获取当前用户Session
     *
     * @return
     */
    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    /**
     * 用户登出
     */
    public static void logout() {
        SecurityUtils.getSubject().logout();
    }

    /**
     * 获取shiro中的当前用户名
     *
     * @return
     */
    public static String getUserName() {
        User user = getUser();
        if (user != null) {
            return user.getUserName();
        }
        return "";
    }

    /**
     * 获取shiro中的当前用户
     *
     * @return
     */
    public static User getUser() {
        try {
            return (User) SecurityUtils.getSubject().getPrincipal();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除用户缓存
     *
     * @param username        用户名称
     * @param isRemoveSession 是否删除session
     */
    public static void deleteCache(String username, boolean isRemoveSession) {

    }
}
