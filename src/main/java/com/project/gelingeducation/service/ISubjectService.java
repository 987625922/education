package com.project.gelingeducation.service;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.domain.Subject;

public interface ISubjectService {

    Object queryAll(Integer currentPage, Integer pageSize);

    Subject findById(Long id);

    Subject insert(Subject video);

    void delectd(Long id);

    void updated(Subject video);
}
