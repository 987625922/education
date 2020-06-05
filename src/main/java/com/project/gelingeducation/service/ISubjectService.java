package com.project.gelingeducation.service;

import com.project.gelingeducation.domain.Subject;

import java.util.List;

public interface ISubjectService {

    List<Subject> findAll();

    Subject findById(Long id);

    long insert(Subject video);

    void delectd(Long id);

    void updated(Subject video);
}
