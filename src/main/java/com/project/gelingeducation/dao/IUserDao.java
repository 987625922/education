package com.project.gelingeducation.dao;

import com.project.gelingeducation.entity.User;
import com.project.gelingeducation.common.dto.PageResult;

import java.util.List;

public interface IUserDao {
    PageResult queryAll(Integer currentPage, Integer pageSize);

    List<User> queryAll();

    User findById(Long id);

    User findByPhone(String account);

    User insert(User user);

    void delect(Long id);

    void delSel(String ids);

    void update(User user);

    void updateCoverImg(Long id, String coverImg);

    PageResult selbyname(String name,Integer currentPage, Integer pageSize);

}
