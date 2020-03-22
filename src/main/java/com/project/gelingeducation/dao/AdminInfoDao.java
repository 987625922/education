package com.project.gelingeducation.dao;

import com.project.gelingeducation.domain.AdminInfo;
import com.project.gelingeducation.dto.PageResult;

import java.util.List;

public interface AdminInfoDao {
    PageResult getLists(int currentPage, int pageSize);

    AdminInfo findById(long id);

    AdminInfo findByPhone(String account);

    AdminInfo insert(AdminInfo adminInfo);

    void delect(long id);

    void delSel(long[] ids);

    void update(AdminInfo adminInfo);

    void updateCoverImg(long id, String coverImg);

    PageResult selbyname(String name,int currentPage, int pageSize);
}
