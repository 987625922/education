package com.project.gelingeducation.controller;


import com.project.gelingeducation.domain.Course;
import com.project.gelingeducation.common.dto.JsonData;
import com.project.gelingeducation.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/course")
@RestController
@Slf4j
public class CourseController {

    @Autowired
    private CourseService courseService;

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/findall")
    public Object findAll() throws Exception {
        return courseService.findAll();
    }

    @RequestMapping(value = "/findbyid", method = RequestMethod.POST)
    public Object findById(long id) throws Exception {
        return courseService.findById(id);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object add(Course course) {
        courseService.insert(course);
        return JsonData.buildSuccess();
    }

    @RequestMapping(value = "/delect", method = RequestMethod.POST)
    public Object delect(long id) {
        courseService.delectd(id);
        return JsonData.buildSuccess();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object update(Course course) {
        courseService.updated(course);
        return JsonData.buildSuccess();
    }
}
