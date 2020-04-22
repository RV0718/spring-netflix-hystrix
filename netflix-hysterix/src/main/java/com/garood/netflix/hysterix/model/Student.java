package com.garood.netflix.hysterix.model;

public class Student {

    private Integer age;
    private String name;
    private String standard;

    public Student(Integer age, String name, String standard) {
        this.age = age;
        this.name = name;
        this.standard = standard;
    }

    public Integer getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getStandard() {
        return standard;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", standard='" + standard + '\'' +
                '}';
    }
}