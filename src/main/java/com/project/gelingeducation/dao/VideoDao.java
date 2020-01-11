package com.project.gelingeducation.dao;

import com.project.gelingeducation.domain.Video;

import java.util.List;

public interface VideoDao {
    List<Video> findAll();

    Video findById(long id);

    long insert(Video video);

    void delect(long id);

    void update(Video video);
}
