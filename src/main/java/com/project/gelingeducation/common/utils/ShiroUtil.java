package com.project.gelingeducation.common.utils;

import com.project.gelingeducation.common.exception.AllException;
import com.project.gelingeducation.common.exception.StatusEnum;
import com.project.gelingeducation.entity.User;
import org.apache.shiro.SecurityUtils;

/**
 * @Author: LL
 * @Description:shiro工具类
 * @Date:Create：in 2020/7/16 14:44
 */
public class ShiroUtil {

    /**
     * 获取shiro中的当前用户名
     *
     * @return
     */
    public static String getUserName() {
        User user = getUser();
        if (user != null){
            return user.getUserName();
        }
        return "";
    }

    /**
     * 获取shiro中的当前用户id
     * @return
     */
    public static Long getUserId() {
        return getUser().getId();
    }

    /**
     * 获取shiro中的当前用户
     *
     * @return
     */
    public static User getUser(){
        User user;
        try {
            user = (User) SecurityUtils.getSubject().getPrincipal();
        } catch (Exception e) {
            throw new AllException(StatusEnum.GET_SHIRO_USER_ERROR);
        }
        return user;
    }

}
