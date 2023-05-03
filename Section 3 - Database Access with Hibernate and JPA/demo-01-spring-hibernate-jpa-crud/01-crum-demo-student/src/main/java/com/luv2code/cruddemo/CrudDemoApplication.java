package com.luv2code.cruddemo;

import java.util.List;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrudDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudDemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
		return runner -> {
			// saveStudent(studentDAO);
			// createMultipleStudents(studentDAO);
			// findStudent(studentDAO);
			queryStudents(studentDAO);
		};
	}

	private void queryStudents(StudentDAO studentDAO) {
		System.out.println("Querying all students");
		List<Student> list = studentDAO.findAll();
		list.forEach(s -> System.out.println(s));
	}

	private void findStudent(StudentDAO studentDAO) {
		System.out.println("Creating Student: ");
		Student student = new Student("Shiv", "Kumar","email.com");

		Student tempStudent = student;
		studentDAO.save(tempStudent);
		System.out.println("Saving student with Generated ID: "+ tempStudent.getId());


		System.out.println("Finding student with ID: "+tempStudent.getId());
		Student foundStudent = studentDAO.findById(tempStudent.getId());
		System.out.println(foundStudent.toString());
	}

	private void createMultipleStudents(StudentDAO studentDAO) {
		System.out.println("Creating 3 student objects: ");
		Student tempStudent1 = new Student("Shiv", "Kumar","email.com");
		Student tempStudent2 = new Student("John", "Doe","email.com");
		Student tempStudent3 = new Student("Mark", "Jones","email.com");

		System.out.println("Saving the students: ");
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);

	}

	private void saveStudent(StudentDAO studentDAO) {
		System.out.println("Defining a student: ");
		Student student = new Student("Shiv", "Kumar","email.com");
		System.out.println(student.toString());

		System.out.println("Saving to database: ");
		Student tempStudent = student;
		studentDAO.save(tempStudent);

		System.out.println("Saved student. ID: "+tempStudent.getId());

		}

}
