package com.luc2code.demo.rest;

import java.util.Arrays;
import java.util.List;

import com.luc2code.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class DemoRestController {

    private List<Student> theStudents;

    @PostConstruct
    public void loadDate() {
        theStudents = Arrays.asList(
                new Student("Shiv", "Kumar"),
                new Student("Sammy", "Ling"),
                new Student("Rohan", "Verma")
        );
    }

    @GetMapping("/hello")
    public String sayHello(){
        return "Hello World!";
    }

    @GetMapping("students")
    public List<Student> getStudents(){
        return theStudents;
    }

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId){
        return theStudents.get(studentId);
    }
}
