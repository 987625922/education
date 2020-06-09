package com.project.gelingeducation.dao;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.domain.Course;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public interface ICourseDao {
    List queryAll();

    Course findById(Long id);

    Long insert(Course video);

    void delect(Long id);

    void update(Course video) throws IllegalAccessException, InvocationTargetException;

    PageResult queryAll(Integer currentPage, Integer pageSize);

    void delMore(String ids);

    PageResult selByNameOrStatusOrPriceOrTeacher(String name, Integer status, Double startPrice,
                                                 Double endPrice, Long teacherId,
                                                 Integer currentPage, Integer pageSize);
}
