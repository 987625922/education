package com.project.gelingeducation.service.Impl;

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
    public void addTeacher(Teacher teacher) {
        teacherDao.insert(teacher);
    }

    @Override
    public Teacher getById(long id) {
        return teacherDao.findById(id);
    }

}
