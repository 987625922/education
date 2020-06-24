package com.project.gelingeducation.service;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.entity.Course;

import java.lang.reflect.InvocationTargetException;

public interface ICourseService {

    Course findById(Long id);

    Long insert(Course course);

    void delect(Long id);

    void update(Course course)  throws IllegalAccessException, InvocationTargetException;

    Object queryAll(Integer currentPage, Integer pageSize);

    void batchesDeletes(String ids);

    PageResult selByNameOrStatusOrPriceOrTeacher(String name, Integer status, Double startPrice,
                                                 Double endPrice, Long teacherId,
                                                 Integer currentPage, Integer pageSize);
    void courseAddTeacher(Long courseId,Long teacherId);
}
