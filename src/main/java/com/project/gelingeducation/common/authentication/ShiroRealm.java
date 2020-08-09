package com.project.gelingeducation.common.authentication;

import com.project.gelingeducation.common.utils.RedisTemplateUtil;
import com.project.gelingeducation.common.utils.ShiroUtil;
import com.project.gelingeducation.entity.Permission;
import com.project.gelingeducation.entity.Role;
import com.project.gelingeducation.entity.User;
import com.project.gelingeducation.service.IRoleService;
import com.project.gelingeducation.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.HashSet;
import java.util.Set;

/**
 * 账号密码匹配和权限匹配
 */
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
    @Lazy
    private IRoleService roleService;

    @Autowired
    RedisTemplateUtil templateUtil;

    /**
     * 授权权限
     * 用户进行权限验证时候shiro会去缓存中早，如果查不到，
     * 就会执行这个方法去查权限，并放入缓存中
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User user = (User) principals.getPrimaryPrincipal();

        Set<String> stringRoleList = new HashSet<>();
        Set<String> stringPermissionList = new HashSet<>();
        Role role = roleService.getRoleByUserId(user.getId());
        //查询角色和权限
        if (role == null || role.getPermissions() == null) {
            throw new AuthenticationException("角色为空");
        }
        stringRoleList.add(role.getName());
        Set<Permission> permissionList = role.getPermissions();
        for (Permission p : permissionList) {
            if (p != null) {
                stringPermissionList.add(p.getPerms());
            }
        }
        //将查询到的权限和角色分别传入AuthorizationInfo
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
        //获取用户输入的token
        String account = (String) authenticationToken.getPrincipal();
        // 通过用户名查询用户信息
        User user = userService.findUserByAccount(account);
        if (user == null) {
            throw new AuthenticationException("用户不存在");
        } else if (user.getStatus() != 1) {
            throw new LockedAccountException("账号被锁定");
        }
        //进行验证
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user,
                user.getPassword(),
                ByteSource.Util.bytes(StringUtils.lowerCase(user.getAccount().toLowerCase())),
                getName()
        );
        //登录成功之后，开始踢人（清除缓存和session）
        ShiroUtil.deleteCache(account, true);
        return authenticationInfo;
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
