package com.project.gelingeducation.dao;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.entity.Video;

import java.util.List;

public interface IVideoDao {
    PageResult queryAll(Integer currentPage, Integer pageSize);

    List<Video> queryAll();

    Video findById(Long id);

    Video insert(Video video);

    void delect(Long id);

    void update(Video video);
}
