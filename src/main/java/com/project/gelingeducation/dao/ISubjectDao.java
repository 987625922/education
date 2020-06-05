package com.project.gelingeducation.dao;

import com.project.gelingeducation.domain.Subject;

import java.util.List;

public interface ISubjectDao {
    List findAll();

    Subject findById(Long id);

    long insert(Subject video);

    void delect(Long id);

    void update(Subject video);
}
