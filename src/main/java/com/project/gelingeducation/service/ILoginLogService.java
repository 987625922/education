package com.project.gelingeducation.service;

import com.project.gelingeducation.entity.LoginLog;
import com.project.gelingeducation.entity.User;

public interface ILoginLogService {

    void insert(LoginLog loginLog);

    Object queryAll(Integer currentPage,Integer pageSize);

    LoginLog getByUserId(Long uid);

    void saveOrUpdateLoginLogByUid(User user);

}
