package com.project.gelingeducation.service;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.domain.Teacher;

public interface ITeacherService {

    void addTeacher(Teacher teacher);

    Teacher getById(long id);

    PageResult getLists(int currentPage, int pageSize);

}
