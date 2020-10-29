package com.cybertek.services;

import com.cybertek.interfaces.Course;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


@Component
public class Java implements Course {
    public void getTeachingHours() {
        System.out.println("Teaching hours: 5");
    }
    @PostConstruct
    public void postConstructor(){
        System.out.println("Executing post constructor");
    }
    @PreDestroy
    public void preDestroy(){
        System.out.println("Executing pre destroy");
    }
}
