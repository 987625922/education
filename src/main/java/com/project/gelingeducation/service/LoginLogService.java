package com.project.gelingeducation.service;

import com.project.gelingeducation.domain.LoginLog;

import java.util.List;

public interface LoginLogService {

    void insert(LoginLog loginLog);

    List<LoginLog> list();
}
