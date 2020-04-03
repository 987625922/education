package com.project.gelingeducation.service.Impl;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.common.exception.AllException;
import com.project.gelingeducation.common.utils.MD5Util;
import com.project.gelingeducation.dao.IUserDao;
import com.project.gelingeducation.domain.Role;
import com.project.gelingeducation.domain.User;
import com.project.gelingeducation.service.IRoleService;
import com.project.gelingeducation.service.IUserService;
import com.project.gelingeducation.service.LoginLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Slf4j
@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private LoginLogService loginLogService;

    @Autowired
    private IRoleService roleService;

    @Override
    public Object register(User user) {
        if (userDao.findByPhone(user.getAccount()) == null) {
            user.setUserName("管理员");
            user.setIsAdaim(1);
            user.setStatus(1);
            return userDao.insert(user);
        } else {
            throw new AllException(-100, "账号已存在");
        }
    }

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

    @Override
    public User login(String account, String password) {
        User info = userDao.findByPhone(account);
        loginLogService.getByUserIdLoginUpdate(info.getId());
        return info;
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(long id) {
        return userDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResult getLists(int currentPage, int pageSize) {
        return userDao.getLists(currentPage, pageSize);
    }

    @Override
    public void updateCoverImg(long id, String coverImg) {
        userDao.updateCoverImg(id, coverImg);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }


    @Override
    public void updatePassword(long id, String oldPassword, String newPassword) {
        User user = userDao.findById(id);
        if (user.getPassword().equals(oldPassword)) {
            user.setPassword(MD5Util.encrypt(user.getAccount(), newPassword));
        } else {
            throw new AllException(-100, "密码错误");
        }
    }

    @Override
    public void delUser(long id) {
        userDao.delect(id);
    }

    @Override
    public void delSelUser(long[] ids) {
        userDao.delSel(ids);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResult selbyname(String name, int currentPage, int pageSize) {
        return userDao.selbyname(name, currentPage, pageSize);
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserByAccount(String account) {
        User user = userDao.findByPhone(account);
        Set<Role> roles = user.getRoles();
        for (Role role : roles) {
            role.setPermissions(role.getPermissions());
            roles.add(role);
        }
        user.setRoles(roles);
        return user;
    }

    /**
     * 添加身份
     *
     * @param id
     * @param roleIds
     */
    @Override
    public void addRole(long id, long[] roleIds) {
        User user = userDao.findById(id);
        for (long roldId : roleIds) {
            Role role = roleService.findByRole(roldId);
            user.getRoles().add(role);
            role.getUsers().add(user);
        }
    }

}
