package com.project.gelingeducation.service.Impl;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.dao.CourseDao;
import com.project.gelingeducation.domain.Course;
import com.project.gelingeducation.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDao courseDao;

    @Override
    @Transactional(readOnly = true)
    public List<Course> findAll() {
        return courseDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Course findById(long id) {
        return courseDao.findById(id);
    }


    @Override
    @Transactional
    public long insert(Course course) {
        return courseDao.insert(course);
    }

    @Override
    @Transactional
    public void delectd(long id) {
        courseDao.delect(id);
    }

    @Override
    @Transactional
    public void updated(Course course) {
        courseDao.update(course);
    }

    /**
     * 页面格式的用户的列表
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public PageResult getLists(int currentPage, int pageSize) {
        return courseDao.getLists(currentPage, pageSize);
    }
}
