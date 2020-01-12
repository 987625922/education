package com.project.gelingeducation.dao;

import com.project.gelingeducation.domain.Subject;

import java.util.List;

public interface SubjectDao {
    List<Subject> findAll();

    Subject findById(long id);

    long insert(Subject video);

    void delect(long id);

    void update(Subject video);
}
