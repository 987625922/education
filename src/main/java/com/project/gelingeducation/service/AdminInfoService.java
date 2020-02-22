package com.project.gelingeducation.service;

import com.project.gelingeducation.domain.AdminInfo;

public interface AdminInfoService {

    Object register(AdminInfo adminInfo);

    Object login(AdminInfo adminInfo);

    AdminInfo findById(long id);

    void updateCoverImg(long id,String coverImg);
}
