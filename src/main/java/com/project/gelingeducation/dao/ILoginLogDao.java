package com.project.gelingeducation.dao;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.entity.LoginLog;

import java.util.List;

public interface ILoginLogDao {

    void insert(LoginLog loginLog);

    PageResult queryAll(Integer currentPage, Integer pageSize);

    List<LoginLog> queryAll();

    LoginLog getByUid(Long uid);

    List getLoginLogByIp(String ip);
}
