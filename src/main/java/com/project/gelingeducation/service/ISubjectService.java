package com.project.gelingeducation.service;

import com.project.gelingeducation.entity.Subject;

/**
 * @Author: LL
 * @Description: 专题的Service接口
 */
public interface ISubjectService {

    /**
     * 获取专题列表
     *
     * @param currentPage 页码
     * @param pageSize    页数
     * @return 页码为空返回全都list，不为空返回分页实体类
     */
    Object queryAll(Integer currentPage, Integer pageSize);

    /**
     * 获取专题
     * @param id 专题id
     * @return 专题实体类
     */
    Subject findById(Long id);

    /**
     * 添加专题
     * @param subject 专题实体类
     * @return
     */
    Subject insert(Subject subject);

    /**
     * 删除专题
     * @param id 专题id
     */
    void delectd(Long id);

    /**
     * 更新专题
     * @param video 专题实体类
     */
    void updated(Subject video);
}
