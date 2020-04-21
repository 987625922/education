package com.project.gelingeducation.service;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.domain.Course;

import java.util.List;

public interface CourseService {

    List<Course> findAll();

    Course findById(long id);

    long insert(Course course);

    void delectd(long id);

    void updated(Course course);

    PageResult getLists(int currentPage, int pageSize);
}
