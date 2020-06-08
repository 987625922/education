package com.project.gelingeducation.dao;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.domain.Video;

import java.util.List;

public interface IVideoDao {
    PageResult list(Integer currentPage, Integer pageSize);

    Video findById(Long id);

    Video insert(Video video);

    void delect(Long id);

    void update(Video video);
}
