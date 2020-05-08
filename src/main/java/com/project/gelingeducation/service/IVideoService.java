package com.project.gelingeducation.service;

import com.project.gelingeducation.domain.Video;

import java.util.List;

public interface IVideoService {

    List<Video> findAll();

    Video findById(long id);

    long insert(Video video);

    void delectd(long id);

    void updated(Video video);
}
