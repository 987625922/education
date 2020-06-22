package com.project.gelingeducation.service;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.entity.Permission;
import com.project.gelingeducation.entity.Role;
import com.project.gelingeducation.entity.User;

import java.util.Set;

public interface IUserService {

    User register(User user);

    User addUser(User user);

    User findById(Long id);

    public Object queryAll(Integer currentPage, Integer pageSize);

    void updateCoverImg(Long id, String coverImg);

    void update(User user);

    void updatePassword(Long id, String oldPassword, String newPassword);

    void delUser(Long id);

    void delSelUser(String ids);

    PageResult selbyname(String name, Integer currentPage, Integer pageSize);

    User findUserByAccount(String account);

    Role findRoleByUserId(Long id);

    Set<Permission> findPermissionByUserId(Long id);

    void addRole(Long userId, Long roleId);

}
