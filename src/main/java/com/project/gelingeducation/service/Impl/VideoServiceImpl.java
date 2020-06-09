package com.project.gelingeducation.service.Impl;

import com.project.gelingeducation.dao.IVideoDao;
import com.project.gelingeducation.domain.Video;
import com.project.gelingeducation.service.IVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
public class VideoServiceImpl implements IVideoService {

    @Autowired
    private IVideoDao videoDao;

    @Override
    public Object queryAll(Integer currentPage, Integer pageSize) {
        if (currentPage != null && pageSize != null) {
            return videoDao.queryAll(currentPage, pageSize);
        } else {
            return videoDao.queryAll();
        }
    }

    @Override
    public Video findById(Long id) {
        return videoDao.findById(id);
    }


    @Override
    @Transactional
    public Video insert(Video video) {
        return videoDao.insert(video);
    }

    @Override
    @Transactional
    public void delectd(Long id) {
        videoDao.delect(id);
    }

    @Override
    @Transactional
    public void updated(Video video) {
        videoDao.update(video);
    }
}
