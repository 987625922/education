package com.project.gelingeducation.service.Impl;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.dao.ITeacherDao;
import com.project.gelingeducation.domain.Teacher;
import com.project.gelingeducation.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class TeacherServiceImpl implements ITeacherService {

    @Autowired
    private ITeacherDao teacherDao;

    @Override
    @Transactional
    public Teacher addTeacher(Teacher teacher) {
        return teacherDao.insert(teacher);
    }

    @Override
    public Teacher getById(Long id) {
        return teacherDao.findById(id);
    }

    @Override
    public Object queryAll(Integer currentPage, Integer pageSize) {
        if (currentPage != null && pageSize != null) {
            return teacherDao.queryAll(currentPage, pageSize);
        }else {
            return teacherDao.queryAll();
        }
    }

    @Override
    @Transactional
    public void delTeacher(Long id) {
        teacherDao.delete(id);
    }

}
