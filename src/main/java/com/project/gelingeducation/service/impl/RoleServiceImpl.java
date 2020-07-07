package com.project.gelingeducation.service.impl;

import com.project.gelingeducation.common.utils.UrlDeconderUtil;
import com.project.gelingeducation.dao.IRoleDao;
import com.project.gelingeducation.entity.Permission;
import com.project.gelingeducation.entity.Role;
import com.project.gelingeducation.service.IPermissionService;
import com.project.gelingeducation.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * @Author: LL
 * @Description: 教师的Service
 */
@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements IRoleService {

    /**
     * 角色实体类的dao
     */
    @Autowired
    private IRoleDao roleDao;

    /**
     * 权限实体类的dao
     */
    @Autowired
    private IPermissionService permissionService;

    /**
     * 获取角色实体类
     *
     * @param id 角色id
     * @return
     */
    @Override
    public Role findByRole(Long id) {
        return roleDao.findById(id);
    }

    /**
     * 添加角色
     *
     * @param role 角色实体类
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addRole(Role role) {
        roleDao.insert(role);
    }

    /**
     * 根据角色id添加权限
     *
     * @param roleId        角色id
     * @param permissionIds 权限id
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addPermissionByIds(Long roleId, Long[] permissionIds) {
        Role role = roleDao.findById(roleId);
        for (int i = 0; i < permissionIds.length; i++) {
            Permission permission = permissionService.getById(permissionIds[i]);
            role.getPermissions().add(permission);
            permission.getRoles().add(role);
        }
    }

    /**
     * 添加角色
     *
     * @param role 角色实体类
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Role role) {

    }

    /**
     * 删除角色
     *
     * @param id 角色id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delRoleById(Long id) {
        roleDao.delRoleById(id);
    }

    /**
     * 搜索全部角色的实体类list
     *
     * @param currentPage 页码
     * @param pageSize    页数
     * @return 页码为空返回全都list，不为空返回分页实体类
     */
    @Override
    public Object queryAll(Integer currentPage, Integer pageSize) {
        if (currentPage != null && pageSize != null) {
            return roleDao.queryAll(currentPage, pageSize);
        } else {
            return roleDao.queryAll();
        }
    }

    /**
     * 搜索创建账号默认的角色
     *
     * @return
     */
    @Override
    public Role findDefault() {
        return roleDao.findDefault();
    }

    /**
     * 根据匹配角色名搜索角色list
     *
     * @param name 匹配的角色名
     * @return
     */
    @Override
    public List<Role> selByName(String name) throws UnsupportedEncodingException {
        return roleDao.selByName(UrlDeconderUtil.decode(name,"UTF-8"));
    }

    /**
     * 根据id字符串删除角色
     *
     * @param roleIds 1,2,3 id字符串
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delMoreRolesByIds(String roleIds) {
        roleDao.delByIds(roleIds);
    }

    /**
     * 获取角色的权限
     *
     * @param roleId
     * @return
     */
    @Override
    public List<Permission> getRoleByIdForPermission(Long roleId) {
        return roleDao.getRoleByIdForPermission(roleId);
    }

    /**
     * 更新角色和权限
     *
     * @param id            角色id
     * @param name          角色名
     * @param remark        角色备注
     * @param permissionIds 1,2,3 权限id字符串
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRoleAndPermission(Long id, String name, String remark,
                                        Long[] permissionIds) {
        Role role = roleDao.findById(id);
        role.setName(name);
        role.setRemark(remark);
        List<Permission> permissions =
                permissionService.getPermissionListByIds(permissionIds);
        for (Permission permission : permissions) {
            role.getPermissions().add(permission);
            permission.getRoles().add(role);
        }
    }

    /**
     * 根据用户id获取角色
     *
     * @param userId 用户id
     * @return 角色id
     */
    @Override
    public Role getRoleByUserId(Long userId) {
        return roleDao.getRoleByUserId(userId);
    }
}
