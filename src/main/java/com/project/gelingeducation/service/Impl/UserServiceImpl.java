package com.project.gelingeducation.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.common.exception.AllException;
import com.project.gelingeducation.common.redis.JedisCacheClient;
import com.project.gelingeducation.common.utils.CommonUtil;
import com.project.gelingeducation.common.utils.MD5Util;
import com.project.gelingeducation.dao.IUserDao;
import com.project.gelingeducation.domain.Permission;
import com.project.gelingeducation.domain.Role;
import com.project.gelingeducation.domain.User;
import com.project.gelingeducation.service.IRoleService;
import com.project.gelingeducation.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private IRoleService roleService;



    /**
     * 注册
     *
     * @param user
     * @return
     */
    @Override
    public Object register(User user) {
        if (userDao.findByPhone(user.getAccount()) == null) {
            user.setUserName("管理员");
            user.setStatus(1);
            user.setPassword(MD5Util.encrypt(user.getAccount().toLowerCase(), user.getPassword()));
            return userDao.insert(user);
        } else {
            throw new AllException(-100, "账号已存在");
        }
    }

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @Override
    public Object addUser(User user) {
        if (userDao.findByPhone(user.getAccount()) == null) {
            user.setUserName("用户名");
            user.setStatus(1);
            user.setPassword(MD5Util.encrypt(user.getAccount().toLowerCase(), user.getPassword()));
            user.setCoverImg("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1582740929074&di=88ebb0f61e464281d947673187acaa59&imgtype=0&src=http%3A%2F%2Fattach.bbs.miui.com%2Fforum%2Fthreadcover%2Fbb%2Fa1%2F1988382.jpg");
            return userDao.insert(user);
        } else {
            throw new AllException(-100, "账号已存在");
        }
    }


    /**
     * 通过id查找用户
     *
     * @param id
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public User findById(long id) {
        User user = userDao.findById(id);
        return user;
    }

    /**
     * 页面格式的用户的列表
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public PageResult getLists(int currentPage, int pageSize) {
        return userDao.getLists(currentPage, pageSize);
    }

    /**
     * 更新用户头像
     *
     * @param id
     * @param coverImg
     */
    @Override
    public void updateCoverImg(long id, String coverImg) {
        userDao.updateCoverImg(id, coverImg);
    }

    /**
     * 更新用户
     *
     * @param user
     */
    @Override
    public void update(User user) {
        userDao.update(user);
    }

    /**
     * @param id
     * @param oldPassword
     * @param newPassword
     */
    @Override
    public void updatePassword(long id, String oldPassword, String newPassword) {
        User user = userDao.findById(id);
        if (user.getPassword().equals(MD5Util.encrypt(user.getAccount().toLowerCase(),
                oldPassword))) {
            user.setPassword(MD5Util.encrypt(user.getAccount().toLowerCase(),
                    newPassword));
        } else {
            throw new AllException(-100, "密码错误");
        }
    }

    /**
     * 删除用户
     *
     * @param id
     */
    @Override
    public void delUser(long id) {
        userDao.delect(id);
    }

    /**
     * 批量删除用户
     *
     * @param ids
     */
    @Override
    public void delSelUser(long[] ids) {
        userDao.delSel(ids);
    }

    /**
     * 通过名字用户格式的用户的list
     *
     * @param name
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public PageResult selbyname(String name, int currentPage, int pageSize) {
        return userDao.selbyname(name, currentPage, pageSize);
    }

    /**
     * 查找用户
     *
     * @param account
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public User findUserByAccount(String account){
        User user = userDao.findByPhone(account);
        return user;
    }

    /**
     * 查找身份
     *
     * @param id
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public Set<Role> findRoleByUserId(long id) {
        User user = userDao.findById(id);
        Set<Role> roles = new HashSet<>();
        for (Role role : user.getRoles()) {
            roles.add(role);
        }
        return roles;
    }

    /**
     * 查找权限
     *
     * @param id
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public Set<Permission> findPermissionByUserId(long id) {
        User user = userDao.findById(id);
        Set<Permission> permissions = new HashSet<>();
        for (Role role : user.getRoles()) {
            for (Permission permission : role.getPermissions()) {
                permissions.add(permission);
            }
        }
        return permissions;
    }

    /**
     * 添加身份
     *
     * @param userId
     * @param roleIds
     */
    @Override
    public void addRole(long userId, long[] roleIds) {
        User user = userDao.findById(userId);
        for (long roldId : roleIds) {
            Role role = roleService.findByRole(roldId);
            user.getRoles().add(role);
            role.getUsers().add(user);
        }
    }

}
