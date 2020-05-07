package com.project.gelingeducation.service;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.domain.Course;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ICourseService {

    PageResult findAll();

    Course findById(long id);

    long insert(Course course);

    void delect(long id);

    void update(Course course);

    PageResult getLists(int currentPage, int pageSize);

    void batchesDeletes(long[] ids);

    PageResult selbyname(String name,int currentPage, int pageSize);

    PageResult selByNameOrStatusOrPriceOrTeacher(String name, int status, double startPrice,
                                                 double endPrice, long teacherId,
                                                 int currentPage, int pageSize);
}
