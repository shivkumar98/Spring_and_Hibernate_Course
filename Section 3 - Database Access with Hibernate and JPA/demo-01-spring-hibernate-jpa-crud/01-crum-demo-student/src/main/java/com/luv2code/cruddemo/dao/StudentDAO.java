package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;


public interface StudentDAO {

    void save(Student student);

    Student findById(Integer id);
}
