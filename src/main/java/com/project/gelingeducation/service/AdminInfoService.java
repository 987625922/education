package com.project.gelingeducation.service;

import com.project.gelingeducation.domain.AdminInfo;
import com.project.gelingeducation.dto.PageResult;

import java.util.List;

public interface AdminInfoService {

    Object register(AdminInfo adminInfo);

    Object addUser(AdminInfo adminInfo);

    Object login(AdminInfo adminInfo);

    AdminInfo findById(long id);

    PageResult getLists(int currentPage, int pageSize);

    void updateCoverImg(long id,String coverImg);

    void update(AdminInfo adminInfo);

    void updatePassword(long id,String oldPassword,String newPassword);

    void delUser(long id);

    void delSelUser(long[] ids);

    PageResult selbyname(String name,int currentPage, int pageSize);
}
