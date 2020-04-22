package com.garood.eureka.client.controller.impl;

import com.garood.eureka.client.controller.ISchoolServiceController;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class SchoolServiceController implements ISchoolServiceController {

    @Autowired
    RestTemplate restTemplate;


    @Override
    public String getStudents(String schoolname) {

        System.out.println("Getting School details for " + schoolname);

        String response = restTemplate.exchange("http://eureka-student-service/getStudentDetailsForSchool/{schoolname}",
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}, schoolname).getBody();

        System.out.println("Response Received as " + response);

        return "School Name -  " + schoolname + " \n Student Details " + response;

    }


    //netflix-hysterix-service
    @Override
    @HystrixCommand(commandKey = "student",groupKey = "student",fallbackMethod = "studentFallBack")
    public String getStudentData() {

        System.out.println("Getting Student's data");

        String response = restTemplate.exchange("http://hysterix-service/getStudentData",
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}).getBody();

        System.out.println("Response Received as " + response);

        return response;

    }

    public String studentFallBack(){
        return "Fall back call";
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
