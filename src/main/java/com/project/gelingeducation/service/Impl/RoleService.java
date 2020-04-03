package com.project.gelingeducation.service.Impl;

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
public class RoleService implements IRoleService {

    @Autowired
    private IRoleDao roleDao;
    @Autowired
    private IPermissionService permissionService;

    @Override
    @Transactional(readOnly = true)
    public Role findByRole(long id) {
        return roleDao.findById(id);
    }

    @Override
    public void add(Role role) {
        roleDao.insert(role);
    }


    @Override
    public void addPermissionByIds(long roleId, long[] permissionIds) {
        Role role = roleDao.findById(roleId);
        for (int i = 0; i < permissionIds.length; i++) {
            Permission permission = permissionService.getById(permissionIds[i]);
            role.getPermissions().add(permission);
            permission.getRoles().add(role);
        }

//        roleDao.insert(role);
//        permissionService.add(permission);

    }

    @Override
    public List<Role> list() {

        return roleDao.list();
    }


}
