package com.project.gelingeducation.service;

import com.project.gelingeducation.entity.Course;

import java.io.UnsupportedEncodingException;

/**
 * @Author: LL
 * @Description: 课程实体类的service接口
 */
public interface ICourseService {

    /**
     * 根据id获取课程
     *
     * @param id 课程id
     * @return 课程实体类
     */
    Course findById(Long id);

    /**
     * 添加课程
     *
     * @param course 课程实体类
     * @return
     */
    Long insert(Course course);

    /**
     * 根据id删除课程
     *
     * @param id 视频id
     */
    void delect(Long id);

    /**
     * 更新课程
     *
     * @param course 课程实体
     */
    void update(Course course);

    /**
     * 获取课程的分页数据
     *
     * @param currentPage 页下标
     * @param pageSize    页数
     * @return 分页实体类
     */
    Object queryAll(Integer currentPage, Integer pageSize);

    /**
     * 批量删除
     *
     * @param ids 格式为1,2,3的课程id字符串
     */
    void batchesDeletes(String ids);

    /**
     * 根据条件搜索课程分页信息
     *
     * @param name        课程名
     * @param status      课程状态
     * @param startPrice  课程搜索开始的价格
     * @param endPrice    课程搜索结束的价格
     * @param teacherId   教师id
     * @param currentPage 页面下标
     * @param pageSize    页数
     * @return 分页实体类
     */
    Object selByNameOrStatusOrPriceOrTeacher(String name, Integer status, Double startPrice,
                                             Double endPrice, Long teacherId,
                                             Integer currentPage, Integer pageSize) throws UnsupportedEncodingException;

    /**
     * 通过专题id获取课程列表
     *
     * @param subjectId 专题id
     * @return
     */
    Object getCourseListBySubjectId(Long subjectId);

    /**
     * 通过视频id获取课程列表
     *
     * @param videoId 专题id
     * @return
     */
    Object getCourseListByVideoId(Long videoId);
}
