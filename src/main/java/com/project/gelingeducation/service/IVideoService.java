package com.project.gelingeducation.service;

import com.project.gelingeducation.entity.Video;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: LL
 * @Description: 视频的Service的接口
 */
public interface IVideoService {

    /**
     * 获取视频实体类的lists
     * 如果没有currentPage就返回所有的lists
     *
     * @param currentPage
     * @param pageSize
     * @return /
     */
    Object queryAll(Integer currentPage, Integer pageSize);

    /**
     * 根据id获取视频实体类
     *
     * @param id 视频id
     * @return 视频实体类
     */
    Video findById(Long id);

    /**
     * 添加视频
     *
     * @param video 视频实体
     * @return 视频实体类
     */
    Video insert(Video video);

    /**
     * 删除视频
     *
     * @param id 视频id
     */
    void delectd(Long id);

    /**
     * 更新视频
     *
     * @param video 视频实体
     */
    void updated(Video video);

    /**
     * 批量删除视频
     * @param ids 视频id 格式为 1,2,3
     */
    void delMore(String ids);

    /**
     * 按条件搜索视频列表
     *
     * @param teacherIds 1,2,3格式的教师id字符串
     * @param name       视频名
     * @param courseIds  1,2,3格式的课程id字符串
     * @return 分页的视频list列表
     */
    Object searchByCriteria(@RequestParam String teacherIds,
                            @RequestParam String name,
                            @RequestParam String courseIds);
}
