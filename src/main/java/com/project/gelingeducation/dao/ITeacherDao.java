package com.project.gelingeducation.dao;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.domain.Teacher;

public interface ITeacherDao {
    Teacher insert(Teacher teacher);
    Teacher findById(Long id);
    PageResult getLists(Integer currentPage, Integer pageSize);
    void delete(Long id);
}
