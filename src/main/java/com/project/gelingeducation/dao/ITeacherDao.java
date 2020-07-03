package com.project.gelingeducation.dao;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.entity.Teacher;

import java.util.List;

/**
 * @Author: LL
 * @Description: 教师的dao的接口
 */
public interface ITeacherDao {

    /**
     * 添加教师
     *
     * @param teacher 教师实体类
     * @return
     */
    Teacher insert(Teacher teacher);

    /**
     * 通过id获取教师
     *
     * @param id 教师id
     * @return
     */
    Teacher findById(Long id);

    /**
     * 获取教师列表
     *
     * @param currentPage 页码
     * @param pageSize    页数
     * @return 返回分页实体类
     */
    PageResult queryAll(Integer currentPage, Integer pageSize);

    /**
     * 获取教师列表
     *
     * @return 全部list
     */
    List queryAll();

    /**
     * 删除教师
     *
     * @param id 教师id
     * @return /
     */
    void delete(Long id);

    /**
     * 搜索教师列表
     *
     * @param name        教师名称
     * @param currentPage 页码
     * @param pageSize    页数
     * @return 分页的教师列表
     */
    Object searchCriteria(String name, Integer currentPage, Integer pageSize);
}
