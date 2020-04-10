package com.project.gelingeducation.service;

import com.project.gelingeducation.domain.Permission;
import com.project.gelingeducation.domain.Role;
import com.project.gelingeducation.domain.User;
import com.project.gelingeducation.common.dto.PageResult;

import java.util.List;
import java.util.Set;

public interface IUserService {

    Object register(User user);

    Object addUser(User user);


    User findById(long id);

    PageResult getLists(int currentPage, int pageSize);

    void updateCoverImg(long id,String coverImg);

    void update(User user);

    void updatePassword(long id,String oldPassword,String newPassword);

    void delUser(long id);

    void delSelUser(long[] ids);

    PageResult selbyname(String name,int currentPage, int pageSize);

    User findUserByAccount(String account);

    Set<Role> findRoleByUserId(long id);

    Set<Permission> findPermissionByUserId(long id);

    void addRole(long userId,long[] roleIds);

}
