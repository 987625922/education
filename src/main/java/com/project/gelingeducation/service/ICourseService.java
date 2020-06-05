package com.project.gelingeducation.service;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.domain.Course;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public interface ICourseService {

    Object findAll();

    Course findById(Long id);

    Long insert(Course course);

    void delect(Long id);

    void update(Course course)  throws IllegalAccessException, InvocationTargetException;

    Object getLists(Integer currentPage, Integer pageSize);

    void batchesDeletes(String ids);

    PageResult selByNameOrStatusOrPriceOrTeacher(String name, Integer status, Double startPrice,
                                                 Double endPrice, Long teacherId,
                                                 Integer currentPage, Integer pageSize);
    void courseAddTeacher(Long courseId,Long teacherId);
}
