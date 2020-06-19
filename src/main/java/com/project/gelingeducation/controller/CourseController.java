package com.project.gelingeducation.controller;

import com.project.gelingeducation.common.annotation.Log;
import com.project.gelingeducation.common.dto.JsonData;
import com.project.gelingeducation.domain.Course;
import com.project.gelingeducation.service.ICourseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;

/**
 * 视频课程的controller
 */
@RequestMapping("/api/course")
@RestController
@Slf4j
public class CourseController {

    @Autowired
    private ICourseService courseService;

    @Log("获取所有课程")
//    @RequiresPermissions("user:root")
    @RequestMapping(value = "/lists")
    public Object lists(@RequestParam(required = false) Integer currentPage,
                        @RequestParam(required = false) Integer pageSize) {
        return JsonData.buildSuccess(courseService.queryAll(currentPage, pageSize));
    }

    @Log("通过id获取单个课程")
    @RequestMapping(value = "/find_by_id")
    public Object findById(Long id) throws Exception {
        return JsonData.buildSuccess(courseService.findById(id));
    }

    @Log("添加课程")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object add(@RequestBody Course course) {
        courseService.insert(course);
        return JsonData.buildSuccess();
    }

    @Log("通过id删除课程")
    @RequestMapping(value = "/delete")
    public Object delete(Long id) {
        courseService.delect(id);
        return JsonData.buildSuccess();
    }

    @Log("更新课程")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object update(@RequestBody Course course) throws InvocationTargetException, IllegalAccessException {
        courseService.update(course);
        return JsonData.buildSuccess();
    }


    @Log("通过id批量删除课程")
    @RequiresPermissions("user:root")
    @RequestMapping(value = "/batches_delete")
    public Object delMore(String ids) {
        courseService.batchesDeletes(ids);
        return JsonData.buildSuccess();
    }


    @Log("多参数搜索课程")
    @RequestMapping(value = "/find_by_name_status_price_teacher")
    public Object findByNameOrStatusOrPriceOrTeacher(@RequestParam(required = false) String name,
                                                     @RequestParam(required = false) Integer status,
                                                     @RequestParam(required = false) Double startPrice,
                                                     @RequestParam(required = false) Double endPrice,
                                                     @RequestParam(required = false) Long teacherId,
                                                     Integer currentPage, Integer pageSize) {
        return JsonData.buildSuccess(courseService.selByNameOrStatusOrPriceOrTeacher(name, status, startPrice,
                endPrice, teacherId, currentPage, pageSize));
    }

}
