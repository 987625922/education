package com.project.gelingeducation.service;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.domain.Video;

import java.util.List;

public interface IVideoService {

    PageResult list(Integer currentPage, Integer pageSize);

    Video findById(Long id);

    Video insert(Video video);

    void delectd(Long id);

    void updated(Video video);
}
