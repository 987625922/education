package com.project.gelingeducation.service;

import com.project.gelingeducation.domain.AdminInfo;

public interface AdminInfoService {

    Object register(AdminInfo adminInfo);

    Object login(AdminInfo adminInfo);
}
