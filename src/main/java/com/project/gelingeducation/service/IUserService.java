package com.project.gelingeducation.service;

import com.project.gelingeducation.domain.User;
import com.project.gelingeducation.dto.PageResult;

public interface IUserService {

    Object register(User user);

    Object addUser(User user);

    Object login(User user);

    User findById(long id);

    PageResult getLists(int currentPage, int pageSize);

    void updateCoverImg(long id,String coverImg);

    void update(User user);

    void updatePassword(long id,String oldPassword,String newPassword);

    void delUser(long id);

    void delSelUser(long[] ids);

    PageResult selbyname(String name,int currentPage, int pageSize);

    User findUserByAccount(String account);
}
