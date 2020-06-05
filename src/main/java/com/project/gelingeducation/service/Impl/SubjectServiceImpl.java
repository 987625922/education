package com.project.gelingeducation.service.Impl;

import com.project.gelingeducation.dao.ISubjectDao;
import com.project.gelingeducation.domain.Subject;
import com.project.gelingeducation.service.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class SubjectServiceImpl implements ISubjectService {

    @Autowired
    private ISubjectDao subjectDao;

    @Override
    @Transactional(readOnly = true)
    public List<Subject> findAll() {
        return subjectDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Subject findById(Long id) {
        return subjectDao.findById(id);
    }


    @Override
    @Transactional
    public long insert(Subject video) {
        return subjectDao.insert(video);
    }

    @Override
    @Transactional
    public void delectd(Long id) {
        subjectDao.delect(id);
    }

    @Override
    @Transactional
    public void updated(Subject video) {
        subjectDao.update(video);
    }
}
