package com.project.gelingeducation.controller;


import com.project.gelingeducation.common.dto.JsonData;
import com.project.gelingeducation.domain.Course;
import com.project.gelingeducation.service.ICourseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 视频课程的controller
 */
@RequestMapping("/course")
@RestController
@Slf4j
public class CourseController {

    @Autowired
    private ICourseService courseService;

    /**
     * 获取所有课程
     *
     * @return
     */
    @RequestMapping(value = "/lists", method = RequestMethod.POST)
    public Object lists(@RequestParam(defaultValue = "1") int currentPage, @RequestParam(defaultValue = "100") int pageSize) {
        return JsonData.buildSuccess(courseService.getLists(currentPage, pageSize));
    }


    @RequestMapping(value = "/findall")
    public Object findAll() throws Exception {
        return courseService.findAll();
    }

    @RequestMapping(value = "/findbyid", method = RequestMethod.POST)
    public Object findById(long id) throws Exception {
        return courseService.findById(id);
    }

    /**
     * 添加
     *
     * @param course
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object add(@RequestBody Course course) {
        courseService.insert(course);
        return JsonData.buildSuccess();
    }

    @RequestMapping(value = "/delect", method = RequestMethod.POST)
    public Object delect(long id) {
        courseService.delect(id);
        return JsonData.buildSuccess();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object update(@RequestBody Course course) {
        courseService.update(course);
        return JsonData.buildSuccess();
    }

    /**
     * 批量删除课程
     */
    @RequiresPermissions("user:root")
    @RequestMapping(value = "/batches_deletes", method = RequestMethod.POST)
    public Object delMoreUser(long[] ids) {
        courseService.batchesDeletes(ids);
        return JsonData.buildSuccess();
    }

    /**
     * 按名字搜索
     */
    @RequestMapping(value = "/sel_by_name_or_status_price_teacher", method = RequestMethod.POST)
    public Object selByNameOrStatusOrPriceOrTeacher(@RequestParam(defaultValue = "", required = false) String name, @RequestParam(defaultValue = "-1") int status,
                                                    @RequestParam(defaultValue = "-1") double startPrice,
                                                    @RequestParam(defaultValue = "-1") double endPrice,
                                                    @RequestParam(defaultValue = "-1") long teacherId,
                                                    int currentPage, int pageSize) {
        return JsonData.buildSuccess(courseService.selByNameOrStatusOrPriceOrTeacher(name, status, startPrice, endPrice,
                teacherId, currentPage, pageSize));
    }

}
