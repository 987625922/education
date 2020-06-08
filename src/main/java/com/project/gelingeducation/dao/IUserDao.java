package com.project.gelingeducation.dao;

import com.project.gelingeducation.domain.Role;
import com.project.gelingeducation.domain.User;
import com.project.gelingeducation.common.dto.PageResult;

public interface IUserDao {
    PageResult getLists(Integer currentPage, Integer pageSize);

    User findById(Long id);

    User findByPhone(String account);

    User insert(User user);

    void delect(Long id);

    void delSel(String ids);

    void update(User user);

    void updateCoverImg(Long id, String coverImg);

    PageResult selbyname(String name,Integer currentPage, Integer pageSize);

}
