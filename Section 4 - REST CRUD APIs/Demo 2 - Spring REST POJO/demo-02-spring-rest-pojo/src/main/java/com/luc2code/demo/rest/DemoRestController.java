package com.luc2code.demo.rest;

import java.util.Arrays;
import java.util.List;

import com.luc2code.demo.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class DemoRestController {

    @GetMapping("/hello")
    public String sayHello(){
        return "Hello World!";
    }

    @GetMapping("students")
    public List<Student> getStudents(){
        return Arrays.asList(new Student("Shiv", "Kumar"),
                new Student("Sammy", "Ling"));
    }
}
