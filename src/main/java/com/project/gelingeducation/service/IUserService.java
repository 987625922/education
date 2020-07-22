package com.project.gelingeducation.service;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.entity.Permission;
import com.project.gelingeducation.entity.Role;
import com.project.gelingeducation.entity.User;

import java.io.UnsupportedEncodingException;
import java.util.Set;

/**
 * @Author: LL
 * @Description: user实体类的service接口
 */
public interface IUserService {

    /**
     * 注册
     *
     * @param user 用户实体类
     * @return 用户实体类
     */
    User register(User user);

    /**
     * 添加用户
     *
     * @param user 用户实体类
     * @return 用户实体类
     */
    User addUser(User user);

    /**
     * 通过id查找用户
     *
     * @param id 用户id
     * @return 用户实体类
     */
    User findById(Long id);

    /**
     * 页面格式的用户的列表
     *
     * @param currentPage 页码
     * @param pageSize    页数
     * @return 页码为空返回全都list，不为空返回分页实体类
     */
    Object queryAll(Integer currentPage, Integer pageSize);

    /**
     * 更新用户头像
     *
     * @param id 用户id
     * @param coverImg 头像地址
     */
    void updateCoverImg(Long id, String coverImg);

    /**
     * 更新用户
     *
     * @param user 用户实体类
     */
    void update(User user);

    /**
     * 修改密码
     *
     * @param id 用户id
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     */
    void updatePassword(Long id, String oldPassword, String newPassword);

    /**
     * 删除用户
     *
     * @param id 用户id
     */
    void delUser(Long id);

    /**
     * 批量删除用户
     *
     * @param ids 1,2,3 id字符串
     */
    void delSelUser(String ids);

    /**
     * 通过名字用户格式的用户的list
     *
     * @param name 用户名
     * @param currentPage 页码
     * @param pageSize    页数
     * @return 返回分页实体类
     */
    PageResult selbyname(String name, Integer currentPage,
                         Integer pageSize) throws UnsupportedEncodingException;

    /**
     * 查找用户
     *
     * @param account 账号
     * @return 用户实体类
     */
    User findUserByAccount(String account);

    /**
     * 查找身份
     *
     * @param id 用户id
     * @return 角色实体类
     */
    Role findRoleByUserId(Long id);

    /**
     * 查找权限
     *
     * @param id 角色id
     * @return 角色列表
     */
    Set<Permission> findPermissionByUserId(Long id);

    /**
     * 添加身份
     *
     * @param userId 用户id
     * @param roleId 角色id
     */
    void addRole(Long userId, Long roleId);
}
