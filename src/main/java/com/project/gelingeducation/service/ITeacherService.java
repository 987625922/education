package com.project.gelingeducation.service;

import com.project.gelingeducation.entity.Teacher;

/**
 * @Author: LL
 * @Description: 教师的Service的接口
 */
public interface ITeacherService {

    /**
     * 添加教师
     *
     * @param teacher 教师实体类
     * @return
     */
    Teacher addTeacher(Teacher teacher);

    /**
     * 通过id获取教师
     *
     * @param id 教师id
     * @return
     */
    Teacher getById(Long id);

    /**
     * 获取教师列表
     *
     * @param currentPage 页码
     * @param pageSize    页数
     * @return 页码为空返回全都list，不为空返回分页实体类
     */
    Object queryAll(Integer currentPage, Integer pageSize);

    /**
     * 删除教师
     *
     * @param id 教师id
     * @return /
     */
    void delTeacher(Long id);

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
