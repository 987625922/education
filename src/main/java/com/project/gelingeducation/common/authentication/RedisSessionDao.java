package com.project.gelingeducation.common.authentication;

import com.project.gelingeducation.common.utils.RedisTemplateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * session缓存，好像如果是自己定义了JwtFilter就没有效果了
 */
@Slf4j
//@Component
public class RedisSessionDao extends AbstractSessionDAO {

    @Autowired
    private RedisTemplateUtil redisTemplateUtil;

    private static final String SHIRO_SESSION_ID_PREFIX = "SHIRO_SESSION_ID:";


    private void saveSession(Session session) {
        if (session == null) {
            return;
        }
        String key = SHIRO_SESSION_ID_PREFIX + session.getId().toString();
        redisTemplateUtil.set(key, session);
    }

    @Override
    protected Serializable doCreate(Session session) {
        log.info("=========== RedisSessionDao doCreate ============");
        Serializable sessionId = generateSessionId(session);
        log.info("创建sessionId;{}", sessionId);
        assignSessionId(session, sessionId);
        saveSession(session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        log.info("=========== RedisSessionDao doReadSession ============");
        if (sessionId == null) {
            return null;
        }
        log.info("读取session信息,sessionId为{}", sessionId);
        String key = SHIRO_SESSION_ID_PREFIX + sessionId.toString();
        return (Session) redisTemplateUtil.get(key);
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        log.info("=========== RedisSessionDao update ============");
        log.info("更新session信息");
        saveSession(session);
    }

    @Override
    public void delete(Session session) {
        log.info("=========== RedisSessionDao delete ============");
        if (session == null || session.getId() == null) {
            return;
        }
        log.info("删除session信息,sessionId为:{}", session.getId().toString());
        String key = SHIRO_SESSION_ID_PREFIX + session.getId().toString();
        redisTemplateUtil.delete(key);
    }

    @Override
    public Collection<Session> getActiveSessions() {
        log.info("=========== RedisSessionDao getActiveSessions ============");
        Set<String> keys = redisTemplateUtil.keys(SHIRO_SESSION_ID_PREFIX + "*");
        Set<Session> sessions = new HashSet<>();
        if (keys.isEmpty()) {
            return sessions;
        }
        for (String key : keys) {
            Session session = (Session) redisTemplateUtil.get(key);
            sessions.add(session);
        }
        return sessions;
    }
}
