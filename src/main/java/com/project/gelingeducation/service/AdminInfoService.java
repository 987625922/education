package com.project.gelingeducation.service;

import com.project.gelingeducation.domain.AdminInfo;

public interface AdminInfoService {

    void register(AdminInfo adminInfo);

    boolean login();
}
