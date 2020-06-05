package com.project.gelingeducation.service;

import com.project.gelingeducation.domain.LoginLog;

import java.util.List;

public interface ILoginLogService {

    void insert(LoginLog loginLog);

    List<LoginLog> list();

    LoginLog getByUserId(Long uid);

    void getByUserIdLoginUpdate(Long uid);

}
