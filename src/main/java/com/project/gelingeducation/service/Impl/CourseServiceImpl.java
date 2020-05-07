package com.project.gelingeducation.service.Impl;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.dao.ICourseDao;
import com.project.gelingeducation.domain.Course;
import com.project.gelingeducation.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
public class CourseServiceImpl implements ICourseService {

    @Autowired
    private ICourseDao courseDao;

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
    public PageResult getLists(int currentPage, int pageSize) {
        if (currentPage != -1&&pageSize != -1) {
            return courseDao.getLists(currentPage, pageSize);
        }else {
            return courseDao.findAll();
        }
    }

    /**
     * 批量删除
     * @param ids
     */
    @Override
    @Transactional
    public void batchesDeletes(long[] ids) {
        courseDao.delSel(ids);
    }

    @Override
    public PageResult selbyname(String name, int currentPage, int pageSize) {
        return courseDao.selbyname(name, currentPage, pageSize);
    }

    @Override
    public PageResult selByNameOrStatusOrPriceOrTeacher(String name, int status, double startPrice, double endPrice,
                                                        long teacherId, int currentPage, int pageSize) {
        return courseDao.selByNameOrStatusOrPriceOrTeacher(name,status,startPrice,endPrice,teacherId,currentPage,pageSize);
    }

}
