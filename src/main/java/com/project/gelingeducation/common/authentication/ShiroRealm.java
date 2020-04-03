package com.project.gelingeducation.common.authentication;

import com.project.gelingeducation.domain.Permission;
import com.project.gelingeducation.domain.Role;
import com.project.gelingeducation.domain.User;
import com.project.gelingeducation.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 自定义实现 ShiroRealm，包含认证和授权两大模块
 */
@Component
@Slf4j
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private IUserService userService;

    /**
     * 进行权限校验的时候回调用
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String account = (String) principals.getPrimaryPrincipal();
        User user = userService.findUserByAccount(account);

        List<String> stringRoleList = new ArrayList<>();
        List<String> stringPermissionList = new ArrayList<>();

        Set<Role> roles = user.getRoles();
        log.debug("roles=====>"+roles.size());
        for (Role role : roles) {
            stringRoleList.add(role.getName());
            Set<Permission> permissionList = role.getPermissions();
            log.debug("permission=====>"+permissionList.size());
            for (Permission p : permissionList) {
                if (p != null) {
                    stringPermissionList.add(p.getName());
                }
            }
        }

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRoles(stringRoleList);
        simpleAuthorizationInfo.addStringPermissions(stringPermissionList);

        return simpleAuthorizationInfo;
    }


    /**
     * 用户登录的时候会调用
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String account = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());

        User user = userService.findUserByAccount(account);

        if (user == null)
            throw new UnknownAccountException("账号未注册！");
        if (!StringUtils.equals(password, user.getPassword()))
            throw new IncorrectCredentialsException("用户名或密码错误！");

        return new SimpleAuthenticationInfo(account, user.getPassword(), this.getClass().getName());
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
