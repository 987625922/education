package com.project.gelingeducation.common.authentication;

import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * @Author: LL
 * @Description: 自定义获取token
 * @Date:Create：in 2020/7/22 11:29
 */
public class ShiroSessionManager extends DefaultWebSessionManager {
    /**
     * 定义常量
     */
    public static final String AUTHORIZARION = "Authorization";
    private static final String REFERENCED_SESSION_ID_SOURCE = "Stateless request";

    /**
     * 重写构造器
     */
    public ShiroSessionManager() {
        super();
        this.setDeleteInvalidSessions(true);
    }

    /**
     * 重写此方法从请求头中获取token
     * 每次请求进来，shiro会去从请求头找Authorization这个key对应的Value（token）
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        String token = WebUtils.toHttp(request).getHeader(AUTHORIZARION);
        //如果请求头中存在token则从请求头中获取token
        if (!StringUtils.isEmpty(token)) {
            //这里逻辑是不允许使用默认的规则从cookie里面提取token，只能通过 Authorization 的handler来获取，
            //如果希望使用默认规则就把下面的注释还原
            //设置reques属性
//            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE
//                    , REFERENCED_SESSION_ID_SOURCE);
//            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, token);
//            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID
//                    , Boolean.TRUE);
            return token;
        } else {
            //负责按默认规则从cookie中提取token
            return super.getSessionId(request, response);
        }
    }
}
