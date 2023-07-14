package com.luc2code.demo.rest;

import java.util.Arrays;
import java.util.List;

import com.luc2code.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> studentsList;

    // the studentList will get initialised exactly once!
    @PostConstruct
    public void loadStudentsData() {
        studentsList = Arrays.asList(
                new Student("Shiv", "Kumar"),
                new Student("Rohan", "Verma")
        );
    }

    // define end point for students
    @GetMapping("/students")
    public List<Student> getStudents() {
        return studentsList;
    }

    // define endpoint for "/students/{studentId}" using index
    @GetMapping("/students/{studentId}")
    public Student getStudentById(@PathVariable int studentId){

        if (studentId>=studentsList.size() || studentId<0) {
            throw new StudentNotFoundException("This studentId: "+studentId+ " does not exist");
        }
        return studentsList.get(studentId);
    }

    // exception handler

}