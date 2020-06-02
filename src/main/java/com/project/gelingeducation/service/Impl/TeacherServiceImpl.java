package com.project.gelingeducation.service.Impl;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.dao.ITeacherDao;
import com.project.gelingeducation.domain.Teacher;
import com.project.gelingeducation.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TeacherServiceImpl implements ITeacherService {

    @Autowired
    private ITeacherDao teacherDao;

    @Override
    public Teacher addTeacher(Teacher teacher) {
        return teacherDao.insert(teacher);
    }

    @Override
    public Teacher getById(Long id) {
        return teacherDao.findById(id);
    }

    @Override
    public PageResult getLists(Integer currentPage, Integer pageSize) {
        return teacherDao.getLists(currentPage, pageSize);
    }

    @Override
    public void delTeacher(Long id) {
        teacherDao.delete(id);
    }

}
