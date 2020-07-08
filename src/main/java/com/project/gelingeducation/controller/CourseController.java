package com.project.gelingeducation.controller;

import com.project.gelingeducation.common.annotation.Log;
import com.project.gelingeducation.common.dto.JsonData;
import com.project.gelingeducation.entity.Course;
import com.project.gelingeducation.service.ICourseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

/**
 * 视频课程的controller
 *
 * @author LL
 */
@RequestMapping("/api/course")
@RestController
@Slf4j
public class CourseController {

    @Autowired
    private ICourseService courseService;

    /**
     * 获取视频列表
     *
     * @param currentPage 页下标（可空）
     * @param pageSize    页数（可空）
     * @return 分页实体类或者全部实体类list
     */
    @Log("获取所有课程")
//    @RequiresPermissions("user:root")
    @RequestMapping(value = "/lists")
    public Object lists(@RequestParam(required = false) Integer currentPage,
                        @RequestParam(required = false) Integer pageSize) {
        return JsonData.buildSuccess(courseService.queryAll(currentPage, pageSize));
    }

    /**
     * 通过id获取单个课程
     *
     * @param id 课程id
     * @return 课程实体类
     */
    @Log("通过id获取单个课程")
    @RequestMapping(value = "/find_by_id")
    public Object findById(Long id) {
        return JsonData.buildSuccess(courseService.findById(id));
    }

    /**
     * 添加课程
     *
     * @param course 课程实体类
     * @return /
     */
    @Log("添加课程")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object add(@RequestBody Course course) {
        courseService.insert(course);
        return JsonData.buildSuccess();
    }

    /**
     * 通过id删除课程
     *
     * @param id 课程id
     * @return /
     */
    @Log("通过id删除课程")
    @RequestMapping(value = "/delete")
    public Object delete(Long id) {
        courseService.delect(id);
        return JsonData.buildSuccess();
    }

    /**
     * 更新课程
     *
     * @param course 课程实体类
     * @return /
     */
    @Log("更新课程")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object update(@RequestBody Course course) {
        courseService.update(course);
        return JsonData.buildSuccess();
    }


    /**
     * 通过ids批量删除课程
     *
     * @param ids 1，2,3 格式的字符串
     * @return
     */
    @Log("通过ids批量删除课程")
    @RequiresPermissions("user:root")
    @RequestMapping(value = "/batches_delete")
    public Object delMore(String ids) {
        courseService.batchesDeletes(ids);
        return JsonData.buildSuccess();
    }

    /**
     * 多参数搜索课程
     *
     * @param name        课程名
     * @param status      课程状态
     * @param startPrice  搜索开始价格
     * @param endPrice    搜索结束价格
     * @param teacherId   老师id
     * @param currentPage 页下标
     * @param pageSize    页数
     * @return 分页的课程实体类
     */
    @Log("多参数搜索课程")
    @RequestMapping(value = "/searchCriteria")
    public Object findByNameOrStatusOrPriceOrTeacher(@RequestParam(required = false) String name,
                                                     @RequestParam(required = false) Integer status,
                                                     @RequestParam(required = false) Double startPrice,
                                                     @RequestParam(required = false) Double endPrice,
                                                     @RequestParam(required = false) Long teacherId,
                                                     Integer currentPage, Integer pageSize) throws UnsupportedEncodingException {
        return JsonData.buildSuccess(courseService.selByNameOrStatusOrPriceOrTeacher(name, status, startPrice,
                endPrice, teacherId, currentPage, pageSize));
    }

    /**
     * 通过专题id获取课程列表
     *
     * @param subjectId 专题id
     * @return
     */
    @Log("通过主题id获取课程列表")
    @RequestMapping(value = "/getCourseListBySubjectId")
    public Object getCourseListBySubjectId(Long subjectId) {
        return JsonData.buildSuccess(courseService.getCourseListBySubjectId(subjectId));
    }
}
