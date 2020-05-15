package com.project.gelingeducation.service.Impl;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.dao.ICourseDao;
import com.project.gelingeducation.dao.ITeacherDao;
import com.project.gelingeducation.domain.Course;
import com.project.gelingeducation.domain.Teacher;
import com.project.gelingeducation.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;


@Service
@Transactional(readOnly = true)
public class CourseServiceImpl implements ICourseService {

    @Autowired
    private ICourseDao courseDao;

    @Autowired
    private ITeacherDao teacherDao;

    @Override
    public PageResult findAll() {
        return courseDao.findAll();
    }

    @Override
    public Course findById(long id) {
        return courseDao.findById(id);
    }

    @Override
    @Transactional
    public long insert(Course course) {
        Date date = new Date();
        course.setLastUpdateTime(date);
        course.setCreateTime(date);
        return courseDao.insert(course);
    }

    @Override
    @Transactional
    public void delect(long id) {
        courseDao.delect(id);
    }

    @Override
    @Transactional
    public void update(Course course) {
        course.setLastUpdateTime(new Date());
        courseDao.update(course);
    }

    @Override
    public void update(Long id, Map<String, String> data) {

    }

    /**
     * 页面格式的用户的列表
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public PageResult getLists(int currentPage, int pageSize) {
        if (currentPage != -1 && pageSize != -1) {
            return courseDao.getLists(currentPage, pageSize);
        } else {
            return courseDao.findAll();
        }
    }

    /**
     * 批量删除
     *
     * @param ids
     */
    @Override
    @Transactional
    public void batchesDeletes(long[] ids) {
        courseDao.delSel(ids);
    }

    @Override
    public PageResult selByNameOrStatusOrPriceOrTeacher(String name, int status, double startPrice, double endPrice,
                                                        long teacherId, int currentPage, int pageSize) {
        return courseDao.selByNameOrStatusOrPriceOrTeacher(name, status, startPrice, endPrice, teacherId, currentPage, pageSize);
    }

    @Override
    @Transactional
    public void courseAddTeacher(long courseId, long teacherId) {
        Course course = courseDao.findById(courseId);
        Teacher teacher = teacherDao.findById(teacherId);

        course.getTeachers().add(teacher);
    }

}
