package com.project.gelingeducation.service;

import com.project.gelingeducation.domain.Video;

import java.util.List;

public interface IVideoService {

    List<Video> findAll();

    Video findById(Long id);

    long insert(Video video);

    void delectd(Long id);

    void updated(Video video);
}
