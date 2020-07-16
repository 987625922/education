package com.project.gelingeducation.dao;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.entity.Video;

import java.util.List;

/**
 * @Author: LL
 * @Description: 视频的dao的接口
 */
public interface IVideoDao {

    /**
     * 根据currentPage和pageSize返回page实体类
     *
     * @param currentPage
     * @param pageSize
     * @return 返回page实体类
     */
    PageResult queryAll(Integer currentPage, Integer pageSize);

    /**
     * 返回所有的视频实体lists
     *
     * @return lists
     */
    List<Video> queryAll();

    /**
     * 根据id查找视频实体
     *
     * @param id 视频id
     * @return 视频实体
     */
    Video findById(Long id);

    /**
     * 添加视频
     *
     * @param video 视频实体
     * @return 视频实体
     */
    Video insert(Video video);

    /**
     * 删除视频
     *
     * @param id 视频id
     */
    void delect(Long id);

    /**
     * 更新视频
     *
     * @param video 视频实体
     */
    void update(Video video);

    /**
     * 批量删除视频
     *
     * @param ids 视频id 格式为 1,2,3
     */
    void delMore(String ids);

    /**
     * 按条件搜索视频列表
     *
     * @param teacherId   教师id
     * @param name        视频名
     * @param currentPage 页码
     * @param pageSize    页数
     * @param courseIds   1,2,3格式的课程id字符串
     * @return 分页的视频list列表
     */
    Object searchByCriteria(String teacherId,
                            String name,
                            String courseIds,
                            Integer currentPage,
                            Integer pageSize);

    /**
     * 获取视频的数量
     *
     * @return 视频数量
     */
    Long getVideosNumber();
}
