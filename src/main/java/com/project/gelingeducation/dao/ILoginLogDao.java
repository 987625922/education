package com.project.gelingeducation.dao;

import com.project.gelingeducation.domain.LoginLog;

import java.util.List;

public interface ILoginLogDao {

    void insert(LoginLog loginLog);

    List<LoginLog> list();

    LoginLog getByUid(Long uid);

    List<LoginLog> getLoginLogByIp(String ip);
}
