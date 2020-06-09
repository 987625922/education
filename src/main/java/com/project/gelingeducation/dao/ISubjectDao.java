package com.project.gelingeducation.dao;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.domain.Subject;

import java.util.List;

public interface ISubjectDao {
    PageResult queryAll(Integer currentPage,Integer pageSize);

    List<Subject> queryAll();

    Subject findById(Long id);

    Subject insert(Subject video);

    void delect(Long id);

    void update(Subject video);
}
