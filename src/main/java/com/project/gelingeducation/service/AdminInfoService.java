package com.project.gelingeducation.service;

import com.project.gelingeducation.domain.AdminInfo;

public interface AdminInfoService {

    Object register(AdminInfo adminInfo);

    Object login(AdminInfo adminInfo);

    AdminInfo findById(long id);

    void updateCoverImg(long id,String coverImg);

    void update(AdminInfo adminInfo);

    void updateInfo(long id, String userName, String eMail, int sex, String note);

    void updatePassword(long id,String oldPassword,String newPassword);
}
