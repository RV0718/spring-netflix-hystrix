package com.garood.netflix.hysterix.controller.impl;

import com.garood.netflix.hysterix.controller.INetflixStudentController;
import com.garood.netflix.hysterix.model.Student;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class NetFlixStudentController implements INetflixStudentController {

    private static Map<String, List<Student>> schoolDB = new HashMap<String, List<Student>>();

    static {
        schoolDB = new HashMap<String, List<Student>>();

        List<Student> lst = new ArrayList<Student>();
        Student std = new Student(12, "Sajal", "ABC");
        lst.add(std);
        std = new Student(14, "Lokesh", "XYZ");
        lst.add(std);

        schoolDB.put("ABC", lst);

        lst = new ArrayList<Student>();
        std = new Student(34, "Kajal", "Class III");
        lst.add(std);
        std = new Student(32, "Sukesh", "Class VI");
        lst.add(std);

        schoolDB.put("XYZ", lst);

    }

    @Override
    public List<Student> getStudentData() {
        List<Student> returnList = new ArrayList<>();
        for (Map.Entry<String, List<Student>> entry : schoolDB.entrySet()) {
            returnList.addAll(entry.getValue());
        }
        System.out.println("sending the data {}===="+returnList);
        return returnList;
    }

    @Override
    @HystrixCommand(commandKey = "student",fallbackMethod = "hysterixFallBackMethod",groupKey = "student")
    public List<Student> getDataBySchool(String schoolname) {
        if(schoolDB.containsKey(schoolname)){
            return schoolDB.get(schoolname);
        }else{
            throw new IllegalArgumentException("Unable to find the school data for " + schoolname);
        }

    }

    public List<Student> hysterixFallBackMethod(String schoolname){
        return Arrays.asList(new Student(0,schoolname,"Fail"));
    }
}
