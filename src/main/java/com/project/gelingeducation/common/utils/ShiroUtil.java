package com.project.gelingeducation.common.utils;

import com.project.gelingeducation.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.LogoutAware;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import com.project.gelingeducation.common.authentication.cache.RedisSessionDAO;

import java.util.Collection;
import java.util.Objects;

/**
 * @Author: LL
 * @Description:shiro工具类
 * @Date:Create：in 2020/7/16 14:44
 */
public class ShiroUtil {
    private ShiroUtil() {
    }


    private static RedisSessionDAO redisSessionDAO = SpringUtil.getBean(RedisSessionDAO.class);

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
     * @param account        用户账号
     * @param isRemoveSession 是否删除session
     */
    public static void deleteCache(String account, boolean isRemoveSession) {
        Session session = null;
        //从缓存中获取Session
        Collection<Session> sessions = redisSessionDAO.getActiveSessions();
        User user;
        Object attribute = null;
        for (Session sessionInfo : sessions) {
            //遍历Session,找到该用户名称对应的Session
            attribute = sessionInfo.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            if (attribute == null) {
                continue;
            }
            //从redis中获取到用户实体
            user = (User) ((SimplePrincipalCollection) attribute).getPrimaryPrincipal();
            if (user == null) {
                continue;
            }
            if (Objects.equals(user.getAccount(), account)) {
                session = sessionInfo;
            }
        }
        if (session == null || attribute == null) {
            return;
        }
        //删除session
        if (isRemoveSession) {
            redisSessionDAO.delete(session);
        }
        //删除Cache，在访问受限接口时会重新授权
        DefaultWebSecurityManager securityManager =
                (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
        Authenticator authc = securityManager.getAuthenticator();
        ((LogoutAware) authc).onLogout((SimplePrincipalCollection) attribute);
    }
}
