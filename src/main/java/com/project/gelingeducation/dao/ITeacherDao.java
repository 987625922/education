package com.project.gelingeducation.dao;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.domain.Teacher;

public interface ITeacherDao {
    void insert(Teacher teacher);
    Teacher findById(long id);
    PageResult getLists(int currentPage, int pageSize);
}
