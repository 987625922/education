package com.project.gelingeducation.service;

import com.project.gelingeducation.domain.Subject;

import java.util.List;

public interface SubjectService {

    List<Subject> findAll();

    Subject findById(long id);

    long insert(Subject video);

    void delectd(long id);

    void updated(Subject video);
}
