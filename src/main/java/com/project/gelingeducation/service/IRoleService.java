package com.project.gelingeducation.service;

import com.project.gelingeducation.domain.Role;

public interface IRoleService {

    Role findByRole(long id);

    void insert(Role role);
}
