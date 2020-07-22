package com.project.gelingeducation.common.authentication;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;

import java.io.Serializable;

/**
 * @Author: LL
 * @Description:自定义sessionId生成器
 * @Date:Create：in 2020/7/22 11:17
 */
public class ShiroSessionIdGenerator implements SessionIdGenerator {

    /**
     * 实现SessionId的生成
     *
     * @param session
     * @return
     */
    @Override
    public Serializable generateId(Session session) {
        Serializable sessionId =
                new JavaUuidSessionIdGenerator().generateId(session);
        return String.format("login_token_%s", sessionId);
    }
}
