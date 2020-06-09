package com.project.gelingeducation.controller;

import com.project.gelingeducation.common.annotation.Log;
import com.project.gelingeducation.common.dto.JsonData;
import com.project.gelingeducation.domain.Subject;
import com.project.gelingeducation.service.ISubjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/subject")
@RestController
@Slf4j
public class SubjectController {

    @Autowired
    private ISubjectService subjectService;

    @Log("获取专题")
    @RequestMapping(value = "/lists")
    public Object lists(@RequestParam(required = false) Integer currentPage,
                        @RequestParam(required = false) Integer pageSize) throws Exception {
        return subjectService.queryAll(currentPage, pageSize);
    }

    @Log("通过id获取专题")
    @RequestMapping(value = "/find_by_id")
    public Object findById(Long id) throws Exception {
        return subjectService.findById(id);
    }

    @Log("添加专题")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object add(Subject subject) {
        subjectService.insert(subject);
        return JsonData.buildSuccess();
    }

    @Log("删除专题")
    @RequestMapping(value = "/delete")
    public Object delete(Long id) {
        subjectService.delectd(id);
        return JsonData.buildSuccess();
    }

    @Log("更新专题")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object update(Subject subject) {
        subjectService.updated(subject);
        return JsonData.buildSuccess();
    }

}
