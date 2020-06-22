package com.project.gelingeducation.controller;

import com.project.gelingeducation.common.annotation.Log;
import com.project.gelingeducation.common.dto.JsonData;
import com.project.gelingeducation.entity.Teacher;
import com.project.gelingeducation.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {

    @Autowired
    private ITeacherService teacherService;

    @Log("获取老师")
    @RequestMapping("/lists")
    public Object queryAll(@RequestParam(required = false) Integer currentPage,
                              @RequestParam(required = false) Integer pageSize) {
        return JsonData.buildSuccess(teacherService.queryAll(currentPage, pageSize));
    }

    @Log("删除老师")
    @RequestMapping(value = "/delete")
    public Object delete(Long id) {
        teacherService.delTeacher(id);
        return JsonData.buildSuccess();
    }

    @Log("添加老师")
    @PostMapping("/add")
    public Object addTeacher(@RequestBody Teacher teacher) {
        teacherService.addTeacher(teacher);
        return JsonData.buildSuccess();
    }
}
