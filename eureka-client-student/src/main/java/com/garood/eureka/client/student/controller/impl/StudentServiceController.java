package com.garood.eureka.client.student.controller.impl;

import com.garood.eureka.client.student.controller.IStudentServiceController;
import com.garood.eureka.client.student.model.Student;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class StudentServiceController implements IStudentServiceController {

    private static Map<String, List<Student>> schoolDB = new HashMap<String, List<Student>>();

    static {
        schoolDB = new HashMap<String, List<Student>>();

        List<Student> lst = new ArrayList<Student>();
        Student std = new Student("Sajal", "Class IV");
        lst.add(std);
        std = new Student("Lokesh", "Class V");
        lst.add(std);

        schoolDB.put("abcschool", lst);

        lst = new ArrayList<Student>();
        std = new Student("Kajal", "Class III");
        lst.add(std);
        std = new Student("Sukesh", "Class VI");
        lst.add(std);

        schoolDB.put("xyzschool", lst);

    }

    @Override
    public List<Student> getStudents(@PathVariable String schoolname) {
        System.out.println("Getting Student details for " + schoolname);

        List<Student> studentList = schoolDB.get(schoolname);
        if (studentList == null) {
            studentList = new ArrayList<Student>();
            Student std = new Student("Not Found", "N/A");
            studentList.add(std);
        }
        return studentList;
    }

}
