package com.project.gelingeducation.service;

import com.project.gelingeducation.domain.LoginLog;
import com.project.gelingeducation.domain.User;

import java.util.List;

public interface ILoginLogService {

    void insert(LoginLog loginLog);

    Object queryAll(Integer currentPage,Integer pageSize);

    LoginLog getByUserId(Long uid);

    void saveOrUpdateLoginLogByUid(User user);

}
