package com.project.gelingeducation.service;

import com.project.gelingeducation.entity.Subject;

public interface ISubjectService {

    Object queryAll(Integer currentPage, Integer pageSize);

    Subject findById(Long id);

    Subject insert(Subject video);

    void delectd(Long id);

    void updated(Subject video);
}
