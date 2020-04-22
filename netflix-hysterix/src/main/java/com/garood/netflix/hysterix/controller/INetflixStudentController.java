package com.garood.netflix.hysterix.controller;


import com.garood.netflix.hysterix.model.Student;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public interface INetflixStudentController {

    @RequestMapping(value = "/getStudentData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    List<Student> getStudentData();

    @RequestMapping(value = "/getDataBySchool/{schoolname}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    List<Student> getDataBySchool(@PathVariable String schoolname);
}
