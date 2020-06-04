package com.project.gelingeducation.service;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.domain.Course;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public interface ICourseService {

    PageResult findAll();

    Course findById(long id);

    long insert(Course course);

    void delect(long id);

    void update(Course course)  throws IllegalAccessException, InvocationTargetException;

    void update(Long id, Map<String, String> data);

    PageResult getLists(int currentPage, int pageSize);

    void batchesDeletes(String[] ids);

    PageResult selByNameOrStatusOrPriceOrTeacher(String name, int status, double startPrice,
                                                 double endPrice, long teacherId,
                                                 int currentPage, int pageSize);
    void courseAddTeacher(long courseId,long teacherId);
}
