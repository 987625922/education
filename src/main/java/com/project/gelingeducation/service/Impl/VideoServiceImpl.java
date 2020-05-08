package com.project.gelingeducation.service.Impl;

import com.project.gelingeducation.dao.IVideoDao;
import com.project.gelingeducation.domain.Video;
import com.project.gelingeducation.service.IVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class VideoServiceImpl implements IVideoService {

    @Autowired
    private IVideoDao videoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Video> findAll() {
        return videoDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Video findById(long id) {
        return videoDao.findById(id);
    }


    @Override
    @Transactional
    public long insert(Video video) {
        return videoDao.insert(video);
    }

    @Override
    @Transactional
    public void delectd(long id) {
        videoDao.delect(id);
    }

    @Override
    @Transactional
    public void updated(Video video) {
        videoDao.update(video);
    }
}
