package com.project.gelingeducation.service.impl;

import com.project.gelingeducation.dao.ISubjectDao;
import com.project.gelingeducation.entity.Subject;
import com.project.gelingeducation.service.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
public class SubjectServiceImpl implements ISubjectService {

    @Autowired
    private ISubjectDao subjectDao;

    @Override
    public Object queryAll(Integer currentPage, Integer pageSize) {
        if (currentPage != null && pageSize != null) {
            return subjectDao.queryAll(currentPage, pageSize);
        }else {
            return subjectDao.queryAll();
        }
    }

    @Override
    public Subject findById(Long id) {
        return subjectDao.findById(id);
    }


    @Override
    @Transactional
    public Subject insert(Subject video) {
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
