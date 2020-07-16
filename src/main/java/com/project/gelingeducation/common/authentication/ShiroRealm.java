package com.project.gelingeducation.common.authentication;

import com.project.gelingeducation.common.config.GLConstant;
import com.project.gelingeducation.common.utils.JwtUtil;
import com.project.gelingeducation.common.utils.RedisTemplateUtil;
import com.project.gelingeducation.common.utils.TokenUtil;
import com.project.gelingeducation.entity.Permission;
import com.project.gelingeducation.entity.Role;
import com.project.gelingeducation.entity.User;
import com.project.gelingeducation.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * 自定义实现 ShiroRealm，包含认证和授权两大模块
 */
@Component(value = "shiroRealm")
@Slf4j
public class ShiroRealm extends AuthorizingRealm {

    /**
     * 不添加@Lazy会报错：
     * Could not obtain transaction-synchronized Session for current thread
     */
    @Autowired
    @Lazy
    private IUserService userService;

    @Autowired
    RedisTemplateUtil templateUtil;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 进行权限校验的时候回调用
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User user = (User) principals.getPrimaryPrincipal();

        Set<String> stringRoleList = new HashSet<>();
        Set<String> stringPermissionList = new HashSet<>();
        Role role = user.getRole();

        stringRoleList.add(role.getName());
        Set<Permission> permissionList = role.getPermissions();
        for (Permission p : permissionList) {
            if (p != null) {
                stringPermissionList.add(p.getPerms());
            }
        }

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(stringRoleList);
        simpleAuthorizationInfo.setStringPermissions(stringPermissionList);

        return simpleAuthorizationInfo;
    }


    /**
     * 用户登录的时候会调用
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {

        String token = (String) authenticationToken.getCredentials();

        String account = JwtUtil.getUsername(token);

        String encryptToken = TokenUtil.encryptToken(token);

        String encryptTokenInRedis =
                (String) templateUtil.get(GLConstant.TOKEN_CACHE_PREFIX + encryptToken + "." + account);

        // 如果找不到，说明已经失效
        if (StringUtils.isBlank(encryptTokenInRedis)) {
            throw new AuthenticationException("token已经过期");
        }

        if (StringUtils.isBlank(account)) {
            throw new AuthenticationException("token校验不通过");
        }

        // 通过用户名查询用户信息
        User user = userService.findUserByAccount(account);

        if (user == null) {
            throw new AuthenticationException("用户名或密码错误");
        }
        if (!JwtUtil.verify(token, account, user.getPassword())) {
            throw new AuthenticationException("token校验不通过");
        }

        return new SimpleAuthenticationInfo(user, token, getName());
    }

    /**
     * 清除当前用户权限缓存
     * 使用方法：在需要清除用户权限的地方注入 ShiroRealm,
     * 然后调用其 clearCache方法。
     */
    public void clearCache() {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }
}
