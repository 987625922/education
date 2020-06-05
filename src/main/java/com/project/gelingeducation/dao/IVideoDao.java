package com.project.gelingeducation.dao;

import com.project.gelingeducation.domain.Video;

import java.util.List;

public interface IVideoDao {
    List<Video> findAll();

    Video findById(Long id);

    long insert(Video video);

    void delect(Long id);

    void update(Video video);
}
