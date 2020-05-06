package com.project.gelingeducation.dao;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.domain.Course;

import java.util.List;

public interface ICourseDao {
    List<Course> findAll();

    Course findById(long id);

    long insert(Course video);

    void delect(long id);

    void update(Course video);

    PageResult getLists(int currentPage, int pageSize);

    void delSel(long[] ids);

    PageResult selbyname(String name,int currentPage, int pageSize);
}
