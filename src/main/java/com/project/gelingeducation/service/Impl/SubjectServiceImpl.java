package com.project.gelingeducation.service.Impl;

import com.project.gelingeducation.dao.SubjectDao;
import com.project.gelingeducation.dao.VideoDao;
import com.project.gelingeducation.domain.Subject;
import com.project.gelingeducation.domain.Video;
import com.project.gelingeducation.service.SubjectService;
import com.project.gelingeducation.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectDao subjectDao;

    @Override
    @Transactional(readOnly = true)
    public List<Subject> findAll() {
        return subjectDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Subject findById(long id) {
        return subjectDao.findById(id);
    }


    @Override
    @Transactional
    public long insert(Subject video) {
        return subjectDao.insert(video);
    }

    @Override
    @Transactional
    public void delectd(long id) {
        subjectDao.delect(id);
    }

    @Override
    @Transactional
    public void updated(Subject video) {
        subjectDao.update(video);
    }
}
