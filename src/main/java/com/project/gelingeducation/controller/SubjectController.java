package com.project.gelingeducation.controller;

import com.project.gelingeducation.common.annotation.Log;
import com.project.gelingeducation.common.dto.JsonData;
import com.project.gelingeducation.entity.Subject;
import com.project.gelingeducation.service.ISubjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author: LL
 * @Description: 课程专题的Controller
 */
@RequestMapping("/api/subject")
@RestController
@Slf4j
public class SubjectController {

    /**
     * 专题的service
     */
    @Autowired
    private ISubjectService subjectService;

    /**
     * 获取专题的lists
     * <p>
     * 如果currentPage为空
     *
     * @param currentPage
     * @param pageSize
     * @return subject实体类的lists
     */
    @Log("获取专题")
    @RequestMapping(value = "/lists")
    public Object lists(@RequestParam(required = false) Integer currentPage,
                        @RequestParam(required = false) Integer pageSize) {
        return JsonData.buildSuccess(subjectService.queryAll(currentPage, pageSize));
    }

    /**
     * 通过id获取专题
     *
     * @param id 专题id
     * @return subject实体类
     */
    @Log("通过id获取专题")
    @RequestMapping(value = "/find_by_id")
    public Object findById(Long id) {
        return JsonData.buildSuccess(subjectService.findById(id));
    }

    /**
     * 添加专题
     *
     * @param subject
     * @return /
     */
    @Log("添加专题")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object add(Subject subject) {
        subjectService.insert(subject);
        return JsonData.buildSuccess();
    }

    /**
     * 删除专题
     *
     * @param id
     * @return /
     */
    @Log("删除专题")
    @RequestMapping(value = "/delete")
    public Object delete(Long id) {
        subjectService.delectd(id);
        return JsonData.buildSuccess();
    }

    /**
     * 更新专题
     *
     * @param subject
     * @return /
     */
    @Log("更新专题")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object update(Subject subject) {
        subjectService.updated(subject);
        return JsonData.buildSuccess();
    }
}
