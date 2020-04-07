package com.project.gelingeducation.dao;

import com.project.gelingeducation.domain.Role;

import java.util.List;

public interface IRoleDao {

    void insert(Role role);

    Role findById(long id);

    List<Role> list();

    void delRoleById(long id);

    long getCount();
}
