package com.project.gelingeducation.service.Impl;

import com.project.gelingeducation.dao.IRoleDao;
import com.project.gelingeducation.domain.Role;
import com.project.gelingeducation.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleService implements IRoleService {

    @Autowired
    private IRoleDao roleDao;

    @Override
    public Role findByRole(long id) {
        return roleDao.findById(id);
    }

    @Override
    public void insert(Role role) {
        roleDao.insert(role);
    }


}
