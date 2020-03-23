package com.project.gelingeducation.dao;

import com.project.gelingeducation.domain.LoginLog;

import java.util.List;

public interface LoginLogDao {

    void insert(LoginLog loginLog);

    List<LoginLog> list();
}
