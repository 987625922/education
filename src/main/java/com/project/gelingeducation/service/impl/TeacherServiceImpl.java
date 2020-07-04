package com.project.gelingeducation.service.impl;

import com.project.gelingeducation.dao.ITeacherDao;
import com.project.gelingeducation.entity.Teacher;
import com.project.gelingeducation.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @Author: LL
 * @Description: 教师的Service
 */
@Service
@Transactional(readOnly = true)
public class TeacherServiceImpl implements ITeacherService {

    /**
     * 教师实体类的dao
     */
    @Autowired
    private ITeacherDao teacherDao;

    /**
     * 添加教师
     *
     * @param teacher 教师实体类
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Teacher addTeacher(Teacher teacher) {
        return teacherDao.insert(teacher);
    }

    /**
     * 通过id获取教师
     *
     * @param id 教师id
     * @return
     */
    @Override
    public Teacher getById(Long id) {
        return teacherDao.findById(id);
    }

    /**
     * 获取教师列表
     *
     * @param currentPage 页码
     * @param pageSize    页数
     * @return 页码为空返回全都list，不为空返回分页实体类
     */
    @Override
    public Object queryAll(Integer currentPage, Integer pageSize) {
        if (currentPage != null && pageSize != null) {
            return teacherDao.queryAll(currentPage, pageSize);
        } else {
            return teacherDao.queryAll();
        }
    }

    /**
     * 删除教师
     *
     * @param id 教师id
     * @return /
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delTeacher(Long id) {
        teacherDao.delete(id);
    }

    /**
     * 搜索教师列表
     *
     * @param name        教师名称
     * @param currentPage 页码
     * @param pageSize    页数
     * @return
     */
    @Override
    public Object searchCriteria(String name, Integer currentPage, Integer pageSize) throws UnsupportedEncodingException {
        return teacherDao.searchCriteria(URLDecoder.decode(name,"UTF-8"), currentPage, pageSize);
    }

}
