package com.project.gelingeducation.dao;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.entity.Subject;

import java.util.List;

/**
 * @Author: LL
 * @Description: 专题实体类的dao
 */
public interface ISubjectDao {

    /**
     * 分页搜索专题
     *
     * @param currentPage 页码
     * @param pageSize    页数
     * @return 分页实体类
     */
    PageResult queryAll(Integer currentPage, Integer pageSize);

    /**
     * 搜索所有的专题
     *
     * @return list的专题
     */
    List<Subject> queryAll();

    /**
     * 根据id获取专题
     *
     * @param id 专题id
     * @return 专题实体类
     */
    Subject findById(Long id);

    /**
     * 添加专题
     *
     * @param subject 专题实体类
     * @return /
     */
    Subject insert(Subject subject);

    /**
     * 删除专题
     *
     * @param id 专题id
     */
    void delect(Long id);

    /**
     * 更新专题
     *
     * @param subject 专题实体类
     */
    void update(Subject subject);

    /**
     * 批量删除专题
     *
     * @param ids 视频id 格式为 1,2,3
     */
    void delMore(String ids);

    /**
     * 条件搜索
     *
     * @param name        专题名称
     * @param courseIds   1,2,3 格式的字符串id
     * @param currentPage 页码
     * @param pageSize    页数
     * @return 分页实体类
     */
    Object searchCriteria(String name, String courseIds, Integer currentPage,
                          Integer pageSize);

    /**
     * 获取专题的数量
     *
     * @return
     */
    Long getTotalNumber();
}
