package com.project.gelingeducation.service;

import com.project.gelingeducation.domain.LoginLog;

import java.util.List;

public interface ILoginLogService {

    void insert(LoginLog loginLog);

    Object queryAll(Integer currentPage,Integer pageSize);

    LoginLog getByUserId(Long uid);

    void saveOrUpdateLoginLogByUid(Long uid);

}
