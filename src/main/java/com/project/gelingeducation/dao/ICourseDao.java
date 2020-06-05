package com.project.gelingeducation.dao;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.domain.Course;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public interface ICourseDao {
    PageResult findAll();

    Course findById(Long id);

    Long insert(Course video);

    void delect(Long id);

    void update(Course video) throws IllegalAccessException, InvocationTargetException;

    void update(Long id, Map<String,String> data);

    PageResult getLists(Integer currentPage, Integer pageSize);

    void delSel(String ids);

    PageResult selByNameOrStatusOrPriceOrTeacher(String name, Integer status, Double startPrice,
                                                 Double endPrice, Long teacherId,
                                                 Integer currentPage, Integer pageSize);
}
