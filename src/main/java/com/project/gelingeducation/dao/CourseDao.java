package com.project.gelingeducation.dao;

import com.project.gelingeducation.domain.Course;

import java.util.List;

public interface CourseDao {
    List<Course> findAll();

    Course findById(long id);

    long insert(Course video);

    void delect(long id);

    void update(Course video);
}
