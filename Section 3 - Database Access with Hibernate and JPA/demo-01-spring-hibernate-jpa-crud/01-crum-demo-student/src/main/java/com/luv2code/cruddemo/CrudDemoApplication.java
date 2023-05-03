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
			// queryStudents(studentDAO);
			// queryStudentsByLastName(studentDAO);
			updateStudent(studentDAO);
		};
	}

	private void updateStudent(StudentDAO studentDAO) {
		int studentId = 1;
		System.out.println("Finding student with id: "+studentId);

		Student student = studentDAO.findById(studentId);
		System.out.println("Found student: "+student.toString());

		System.out.println("Updating name to scooby: ");
		student.setLastName("Scooby");
		System.out.println(student.toString());

		System.out.println("Updating row in DB");
		studentDAO.UpdateStudent(student);

		System.out.println("Retrieving student:");
		System.out.println(studentDAO.findById(studentId).toString());


	}

	private void queryStudentsByLastName(StudentDAO studentDAO){
		System.out.println("Querying students by last name:");
		List<Student> students = studentDAO.findByLastName("Kumar");
		students.forEach(System.out::println);
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
