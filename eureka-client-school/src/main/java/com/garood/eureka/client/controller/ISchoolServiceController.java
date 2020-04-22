package com.garood.eureka.client.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public interface ISchoolServiceController {

    @RequestMapping(value = "/getSchoolDetails/{schoolname}", method = RequestMethod.GET)
    String getStudents(@PathVariable String schoolname);

    @RequestMapping(value = "/getStudentData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    String getStudentData();
}
