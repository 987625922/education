package com.project.gelingeducation.dao.Impl;

import com.project.gelingeducation.dao.ITeacherDao;
import com.project.gelingeducation.domain.Teacher;
import org.springframework.stereotype.Repository;

@Repository
public class TeacherDaoImpl extends BaseDao implements ITeacherDao {

    @Override
    public void insert(Teacher teacher) {
        getSession().save(teacher);
    }

    @Override
    public Teacher findById(long id) {
        return getSession().get(Teacher.class,id);
    }
}
