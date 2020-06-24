package com.project.gelingeducation.service;

import com.project.gelingeducation.entity.Video;

public interface IVideoService {

    Object queryAll(Integer currentPage, Integer pageSize);

    Video findById(Long id);

    Video insert(Video video);

    void delectd(Long id);

    void updated(Video video);
}
