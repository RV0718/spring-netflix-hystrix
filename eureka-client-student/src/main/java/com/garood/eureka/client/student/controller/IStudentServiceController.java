package com.garood.eureka.client.student.controller;

import com.garood.eureka.client.student.model.Student;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public interface IStudentServiceController {

    @RequestMapping(value = "/getStudentDetailsForSchool/{schoolname}", method = RequestMethod.GET)
    List<Student> getStudents(@PathVariable String schoolname);
}
