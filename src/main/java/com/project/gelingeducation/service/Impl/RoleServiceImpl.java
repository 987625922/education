package com.project.gelingeducation.service.Impl;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.dao.IRoleDao;
import com.project.gelingeducation.domain.Permission;
import com.project.gelingeducation.domain.Role;
import com.project.gelingeducation.service.IPermissionService;
import com.project.gelingeducation.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;

    @Autowired
    private IPermissionService permissionService;

    @Override
    @Transactional(readOnly = true)
    public Role findByRole(Long id) {
        return roleDao.findById(id);
    }

    @Override
    public void addRole(Role role) {
        roleDao.insert(role);
    }

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
    public List<Role> list() {
        return roleDao.list();
    }

    @Override
    public void delRoleById(Long id) {
        roleDao.delRoleById(id);
    }

    @Override
    public PageResult getRolePageList(Integer currentPage, Integer pageSize) {
        return roleDao.getRolePageList(currentPage, pageSize);
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
    public void delMoreRolesByIds(Long[] roleIds) {
        roleDao.delByIds(roleIds);
    }

    @Override
    public List<Permission> getRoleByIdForPermission(Long roleId) {
        return roleDao.getRoleByIdForPermission(roleId);
    }

    @Override
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


}
