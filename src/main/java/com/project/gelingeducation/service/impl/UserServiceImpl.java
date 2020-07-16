package com.project.gelingeducation.service.impl;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.common.exception.AllException;
import com.project.gelingeducation.common.exception.StatusEnum;
import com.project.gelingeducation.common.utils.Md5Util;
import com.project.gelingeducation.dao.IUserDao;
import com.project.gelingeducation.entity.Permission;
import com.project.gelingeducation.entity.Role;
import com.project.gelingeducation.entity.User;
import com.project.gelingeducation.service.IRoleService;
import com.project.gelingeducation.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: LL
 * @Description: user实体类的service
 */
@Slf4j
@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements IUserService {

    /**
     * user实体类的dao
     */
    @Autowired
    private IUserDao userDao;

    /**
     * role实体类的dao
     */
    @Autowired
    private IRoleService roleService;

    /**
     * 注册
     *
     * @param user 用户实体类
     * @return 用户实体类
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public User register(User user) {
        if (userDao.findByPhone(user.getAccount()) == null) {
            user.setUserName("管理员");
            user.setStatus(1);
            user.setPassword(Md5Util.encrypt(user.getAccount().toLowerCase(), user.getPassword()));
            return userDao.insert(user);
        } else {
            throw new AllException(StatusEnum.ACCOUNT_ALREADY_EXISTS);
        }
    }

    /**
     * 添加用户
     *
     * @param user 用户实体类
     * @return 用户实体类
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public User addUser(User user) {
        if (user.getRole() == null) {
            user.setRole(roleService.findDefault());
        }
        if (userDao.findByPhone(user.getAccount()) == null) {
            user.setUserName("用户名");
            user.setStatus(1);
            user.setPassword(Md5Util.encrypt(user.getAccount().toLowerCase(), user.getPassword()));
            user.setCoverImg("https://timgsa.baidu.com/timg?image&quality=80&size=b9999" +
                    "_10000&sec=1582740929074&di=88ebb0f61e464281d947673187acaa59&imgtype=0" +
                    "&src=http%3A%2F%2Fattach.bbs.miui.com%2Fforum%2Fthreadcover%2Fbb%2Fa1%2F1" +
                    "988382.jpg");
            return userDao.insert(user);
        } else {
            throw new AllException(StatusEnum.ACCOUNT_ALREADY_EXISTS);
        }
    }


    /**
     * 通过id查找用户
     *
     * @param id 用户id
     * @return 用户实体类
     */
    @Override
    public User findById(Long id) {
        User user = userDao.findById(id);
        return user;
    }

    /**
     * 页面格式的用户的列表
     *
     * @param currentPage 页码
     * @param pageSize    页数
     * @return 页码为空返回全都list，不为空返回分页实体类
     */
    @Override
    public Object queryAll(Integer currentPage, Integer pageSize) {
        if (currentPage != null && pageSize != null) {
            return userDao.queryAll(currentPage, pageSize);
        }else {
            return userDao.queryAll();
        }
    }

    /**
     * 更新用户头像
     *
     * @param id 用户id
     * @param coverImg 头像地址
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateCoverImg(Long id, String coverImg) {
        userDao.updateCoverImg(id, coverImg);
    }

    /**
     * 更新用户
     *
     * @param user 用户实体类
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(User user) {
        userDao.update(user);
    }

    /**
     * 修改密码
     *
     * @param id 用户id
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePassword(Long id, String oldPassword, String newPassword) {
        User user = userDao.findById(id);
        if (user.getPassword().equals(Md5Util.encrypt(user.getAccount().toLowerCase(),
                oldPassword))) {
            user.setPassword(Md5Util.encrypt(user.getAccount().toLowerCase(),
                    newPassword));
        } else {
            throw new AllException(StatusEnum.ACCOUNT_PASSWORD_ERROR);
        }
    }

    /**
     * 删除用户
     *
     * @param id 用户id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delUser(Long id) {
        userDao.delect(id);
    }

    /**
     * 批量删除用户
     *
     * @param ids 1,2,3 id字符串
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delSelUser(String ids) {
        userDao.delSel(ids);
    }

    /**
     * 通过名字用户格式的用户的list
     *
     * @param name 用户名
     * @param currentPage 页码
     * @param pageSize    页数
     * @return 返回分页实体类
     */
    @Override
    public PageResult selbyname(String name, Integer currentPage, Integer pageSize) throws UnsupportedEncodingException {
        return userDao.selbyname(URLDecoder.decode(name,"UTF-8"), currentPage, pageSize);
    }

    /**
     * 查找用户
     *
     * @param account 账号
     * @return 用户实体类
     */
    @Override
    public User findUserByAccount(String account) {
        User user = userDao.findByPhone(account);
        return user;
    }

    /**
     * 查找身份
     *
     * @param id 用户id
     * @return 角色实体类
     */
    @Override
    public Role findRoleByUserId(Long id) {
        User user = userDao.findById(id);
        return user.getRole();
    }

    /**
     * 查找权限
     *
     * @param id 角色id
     * @return 角色列表
     */
    @Override
    public Set<Permission> findPermissionByUserId(Long id) {
        User user = userDao.findById(id);
        Role role = user.getRole();
        Set<Permission> permissions = new HashSet<>();
        for (Permission permission : role.getPermissions()) {
            permissions.add(permission);
        }
        return permissions;
    }

    /**
     * 添加身份
     *
     * @param userId 用户id
     * @param roleId 角色id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addRole(Long userId, Long roleId) {
        User user = userDao.findById(userId);
        Role role = roleService.findByRole(roleId);
        user.setRole(role);
    }
}
