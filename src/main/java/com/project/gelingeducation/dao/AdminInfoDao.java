package com.project.gelingeducation.dao;

import com.project.gelingeducation.domain.AdminInfo;

import java.util.List;

public interface AdminInfoDao {
    List<AdminInfo> findAll();

    AdminInfo findById(long id);

    AdminInfo findByPhone(String phone);

    AdminInfo insert(AdminInfo adminInfo);

    void delect(long id);

    void update(AdminInfo adminInfo);

    void updateCoverImg(long id, String coverImg);

}
