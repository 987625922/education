package com.project.gelingeducation.dao;

import com.project.gelingeducation.domain.Role;

public interface IRoleDao {

    void insert(Role role);

    Role findById(long id);

}
