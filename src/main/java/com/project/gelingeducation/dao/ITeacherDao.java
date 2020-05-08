package com.project.gelingeducation.dao;

import com.project.gelingeducation.domain.Teacher;

public interface ITeacherDao {
    void insert(Teacher teacher);
    Teacher findById(long id);
}
