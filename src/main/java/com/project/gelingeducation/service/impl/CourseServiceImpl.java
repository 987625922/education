package com.project.gelingeducation.service.impl;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.dao.ICourseDao;
import com.project.gelingeducation.dao.ITeacherDao;
import com.project.gelingeducation.entity.Course;
import com.project.gelingeducation.entity.Teacher;
import com.project.gelingeducation.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;


@Service
@Transactional(readOnly = true)
public class CourseServiceImpl implements ICourseService {

    @Autowired
    private ICourseDao courseDao;

    @Autowired
    private ITeacherDao teacherDao;

    @Override
    public Course findById(Long id) {
        return courseDao.findById(id);
    }

    @Override
    @Transactional
    public Long insert(Course course) {
        Date date = new Date();
        course.setLastUpdateTime(date);
        course.setCreateTime(date);
        return courseDao.insert(course);
    }

    @Override
    @Transactional
    public void delect(Long id) {
        courseDao.delect(id);
    }

    @Override
    @Transactional
    public void update(Course course)
            throws IllegalAccessException, InvocationTargetException {
        course.setLastUpdateTime(new Date());
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
    public Object queryAll(Integer currentPage, Integer pageSize) {
        if (currentPage != null && pageSize != null) {
            return courseDao.queryAll(currentPage, pageSize);
        } else {
            return courseDao.queryAll();
        }
    }

    /**
     * 批量删除
     *
     * @param ids
     */
    @Override
    @Transactional
    public void batchesDeletes(String ids) {
        courseDao.delMore(ids);
    }

    @Override
    public PageResult selByNameOrStatusOrPriceOrTeacher(String name, Integer status, Double startPrice, Double endPrice,
                                                        Long teacherId, Integer currentPage, Integer pageSize) {
        return courseDao.selByNameOrStatusOrPriceOrTeacher(name, status, startPrice, endPrice, teacherId, currentPage, pageSize);
    }

    @Override
    @Transactional
    public void courseAddTeacher(Long courseId, Long teacherId) {
        Course course = courseDao.findById(courseId);
        Teacher teacher = teacherDao.findById(teacherId);

        course.getTeachers().add(teacher);
    }

}
