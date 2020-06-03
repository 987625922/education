package com.project.gelingeducation.controller;

import com.project.gelingeducation.common.dto.JsonData;
import com.project.gelingeducation.domain.Teacher;
import com.project.gelingeducation.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {

    @Autowired
    private ITeacherService teacherService;

    @RequestMapping("/lists")
    public Object getPagelist(@RequestParam Integer currentPage,
                              @RequestParam Integer pageSize) {
        return JsonData.buildSuccess(teacherService.getLists(currentPage, pageSize));
    }


    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete")
    public Object delTeacher(Long id) {
        teacherService.delTeacher(id);
        return JsonData.buildSuccess();
    }

    @PostMapping("/add")
    public Object addTeacher(@RequestBody Teacher teacher) {
        teacherService.addTeacher(teacher);
        return JsonData.buildSuccess();
    }
}
