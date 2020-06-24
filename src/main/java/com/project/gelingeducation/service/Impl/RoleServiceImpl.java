package com.project.gelingeducation.service.Impl;

import com.project.gelingeducation.dao.IRoleDao;
import com.project.gelingeducation.entity.Permission;
import com.project.gelingeducation.entity.Role;
import com.project.gelingeducation.service.IPermissionService;
import com.project.gelingeducation.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;

    @Autowired
    private IPermissionService permissionService;

    @Override
    public Role findByRole(Long id) {

        return roleDao.findById(id);
    }

    @Transactional
    @Override
    public void addRole(Role role) {
        roleDao.insert(role);
    }

    @Transactional
    @Override
    public void addPermissionByIds(Long roleId, Long[] permissionIds) {
        Role role = roleDao.findById(roleId);
        for (int i = 0; i < permissionIds.length; i++) {
            Permission permission = permissionService.getById(permissionIds[i]);
            role.getPermissions().add(permission);
            permission.getRoles().add(role);
        }
    }


    @Override
    @Transactional
    public void update(Role role) {

    }

    @Override
    @Transactional
    public void delRoleById(Long id) {
        roleDao.delRoleById(id);
    }

    @Override
    public Object queryAll(Integer currentPage, Integer pageSize) {
        if (currentPage != null && pageSize != null) {
            return roleDao.queryAll(currentPage, pageSize);
        } else {
            return roleDao.queryAll();
        }
    }

    @Override
    public Role findDefault() {
        return roleDao.findDefault();
    }

    @Override
    public List<Role> selByName(String name) {
        return roleDao.selByName(name);
    }

    @Override
    @Transactional
    public void delMoreRolesByIds(String roleIds) {
        roleDao.delByIds(roleIds);
    }

    @Override
    public List<Permission> getRoleByIdForPermission(Long roleId) {
        return roleDao.getRoleByIdForPermission(roleId);
    }

    @Override
    @Transactional
    public void updateRoleAndPermission(Long id, String name, String remark,
                                        Long[] permissionIds) {
        Role role = roleDao.findById(id);
        role.setName(name);
        role.setRemark(remark);
        List<Permission> permissions = permissionService.getPermissionListByIds(permissionIds);
        for (Permission permission : permissions) {
            role.getPermissions().add(permission);
            permission.getRoles().add(role);
        }
    }

    @Override
    public Role getRoleByUserId(Long userId) {
        return roleDao.getRoleByUserId(userId);
    }


}
