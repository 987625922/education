package com.project.gelingeducation.controller;

import com.project.gelingeducation.service.SubjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/subject")
@RestController
@Slf4j
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/findall")
    public Object findAll() throws Exception {
        return subjectService.findAll();
    }

}
