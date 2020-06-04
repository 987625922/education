package com.project.gelingeducation.dao;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.domain.Course;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public interface ICourseDao {
    PageResult findAll();

    Course findById(long id);

    long insert(Course video);

    void delect(long id);

    void update(Course video) throws IllegalAccessException, InvocationTargetException;

    void update(Long id, Map<String,String> data);

    PageResult getLists(int currentPage, int pageSize);

    void delSel(String ids);

    PageResult selByNameOrStatusOrPriceOrTeacher(String name, int status, double startPrice,
                                                 double endPrice, long teacherId,
                                                 int currentPage, int pageSize);
}
