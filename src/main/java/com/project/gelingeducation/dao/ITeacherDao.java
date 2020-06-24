package com.project.gelingeducation.dao;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.entity.Teacher;

import java.util.List;

public interface ITeacherDao {
    Teacher insert(Teacher teacher);
    Teacher findById(Long id);
    PageResult queryAll(Integer currentPage, Integer pageSize);
    List<Teacher> queryAll();
    void delete(Long id);
}
