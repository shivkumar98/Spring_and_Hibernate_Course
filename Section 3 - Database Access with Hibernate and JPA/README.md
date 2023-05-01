<link rel="stylesheet" href="../style.css" />

# 🟪 Section 3 - Spring Boot 3 - Database Access with Hibernate/JPA CRUD

# 🧠 3.1 Introduction

## 🟦 Overview

We shall go through the following topics:

1) What is Hibernate? 🤔

2) Benefits of Hibernate 😊 

3) What is JPA? 🤔

4) Benefits of JPA 😊 

## 🟦 What is Hibernate? 🤔

* 🎃 **Hibernate is a framework for persisting Java objects to a database** 🎃

* It's a very popular framework used by enterprise applications:

<img  width="500px" src="screenshots/2023-04-25-15-18-37.png">

## 🟦 Benefits of Hibernate 😊

* Handles all low level SQL code ✅
* Minimises amount of JDBC code needed ✅
* It provides the Object-to-Relational Mapping (ORM) ✅

## 🟦 How does Hibernate work?? 🤔

* The developer simply defines the mapping between the Java class and database table:

<img  width="500px" src="screenshots/2023-04-25-15-22-13.png">

* These mappings can be made through XML, Java code or annotations

## 🟦 What is JPA? 🤔

* 🎃 **JPA is Jakarta Persistence API (previously Java Persistence API). It defines a set of interfaces and acts as a standard API for ORM** 🎃

* JPA Vendor Implementations are frameworks which provide implementation for JPA. Hibernate and EclipseLink are both JPA implementations

## 🟦 Benefits of JPA 😊

* A standard API means you are not locked to a specific vendor, so you can switch implementations✅
* This maintains portability and flexibility of code ✅
* It provides the Object-to-Relational Mapping (ORM) ✅


## 🟦 Saving Java Object with JPA 🤔

* Here's some code to persist to the database:

```java
// create Java object
Student student = new Student("Shiv", "Kumar", "shiv@email.com");

// save to database
entityManager.persist(student);
```

* The `entityManager` is a JPA helper object which has methods which execute SQL queries on the database❗

## 🟦 Retrieving a Java Object with JPA 🤔

* Here's some code to persist to the database:

```java
// retrieve from database using primary key:
int theId = 1;
Student myStudent = entityManager.find(Student.class, theId);
```

## 🟦 Querying for Java Objects 🤔

* Here's some code for querying Java objects from the DB:

```java
TypedQuery<Student> query = entityManager.query("FROM Students", Student.class);
List<Student> listOfStudents = query.getResultList();
```

## How does Hibernate/JPA relate to JDBC?

* Hibernate/JPA uses JDBC for all database communications, its another layer of Abstraction!

<br>

# 🧠 3.2 Setting Up Database Table

* I download MySQL Server using the Windows installer

 * We shall setup the database using some starter files:

    1) `01.create-user.sql`

    2) `02-student-tracker.sql`

* I take the folder [00-starter-sql-scripts](/00-starter-sql-scripts/) and copy it to a new demo folder ([here](/demo-01-spring-hibernate-jpa-crud/))

* I open up the create user sql in MySQL workbench and executre the script.

* I can see the new user in the admin and privelleges page:

    <img  width="400px" src="screenshots/2023-04-27-14-38-23.png">

* I create a new connection for the springstudent connection, and succesfully test the connection:

    <img  width="500px" src="screenshots/2023-04-27-14-39-49.png">

* I use the new connection, I look at the schema:

    <img  width="200px" src="screenshots/2023-04-27-14-40-49.png">

* I open up the student tracker SQL script and execute it. Checking the schema now:

    <img  width="200px" src="screenshots/2023-04-27-14-41-40.png">

* The database is now setup!!!

<br>

# 🧠 3.3 Setting Up Spring Boot Project

## 🟦 Automatic Data Source Configuration

* ❗Hibernate is the default implementation of JPA❗

* `EntityManager` from JPA is the main component for creating enquiries ✅

* Based on configuration, Spring will create beans for `DataSource` and `EntityManager`

* These can be injected into the app

## 🟦 Setting Up Project

* We shall use Spring Intitialzr to add dependencies for:
    
    - MySQL Driver `mysql-connecter-j` 👷
    - Spring Data JPA `spring-boot-starter-data-jpa` 👷

* Spring Boot will automatically configure data source for you and get the DB connection info from `application.properties` - which will contain url, username and passeword

* We shall make a simple command line app to focus on JPA/Hibernate

## 🟦 Code Demo

* I go to spring initialzr and create the following project:

<img  width="500px" src="screenshots/2023-04-27-14-58-28.png">

* I unzip the code folder [here](/demo-01-spring-hibernate-jpa-crud/)

* I open up `CrudDemoApplication`:

```java
@SpringBootApplication
public class CrudDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudDemoApplication.class, args);
	}

}
```

* I create a new Bean `CommandLineRunner` (from Spring Boot):

```java
@SpringBootApplication
public class CrudDemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(CrudDemoApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(String[] args){
		return runner -> {
			System.out.println("Hello World");
		};
	}
}
```


* I update the `application.properties`:

```properties
# the port number comes from the connection in MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/student_tracker
spring.datasource.username=springstudent
spring.datasource.password=springstudent
```

* We can see that the application is running:

    <img  width="700px" src="screenshots/2023-04-27-15-07-36.png">

* ❗ We can switch off the spring boot banner from displaying in console with an additional property. We can also set the logging level to warn ❗

```properties
# disable spring banner
spring.main.banner-mode=off

# reduce logging level
logging.level.root=warn
```

<br>

# 🧠 3.4 JPA Annotations

* We shall map a Java class to a database table. The Java class will be a `Student` class with id, firstName, lastName, email fields

## 🟦 Entity Class

* The entity class must be annotated with `@Entity`❗ and must have public or protected no-argument constructor❗ (there can be other constructors)

## 🟦 Annotations

* `@Entity` - we declare this above the class we wish to map✅ If the database table has a different name to the class then we can use the `@Table` annotation to specify the DB table name😱

* `@Column` is used to map a field of the class to the column✅ We can specify the name of the database column explicitly using `name` in brackets

* ⚠️ The `@Column` annotation is completely optional if the table name is the same as the field name - however this is discouraged ⚠️

## 🟦 Primary Key

* 🎃A primary key is a unique, non-null value which can be used to reference a single column🎃

## 🟦 MySQL Auto Increment

* We can make use of `AUTO_INCREMENT` in MySQL such that the column will always have a generated unique value✅ This is especially useful for primary keys😊

```sql
CREATE TABLE student {
	id INT NOT NULL AUTO_INCREMENT,
	// ... ,
	PRIMARY KEY(id)
}
```

## 🟦 JPA Identity - Primary Key

* JPA has a `@GeneratedValue` annotation allows us to enable the database to handle generation of this column. We specify the strategy in brackets:

```java
public class Student {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;

	//...
}
```

### 🟥 Generation Strategy

* We have the following strategies:

| Name					| 	Description		|
| --------------------- | ----------------- |
| `GenerationType.AUTO`	|	Picks appropiate strategy for particular database |
| `GenerationType.IDENTITY` | Assigns primary keys using DB identity column |
| `GenerationType.SEQUENCE`  | Assigns primary keys using a database sequence |
| `GenerationType.TABLE`     | As


😱 You can define your own generation strategy by creating an implementation of `org.hibernate.id.IdentifierGenerator` and overriding the `generate()` method 😱


<hr>

## 👨‍💻 Coding Demo 👨‍💻

* I create a new package called `entity` and create a `Student` class in it:

<img  width="500px" src="screenshots/2023-05-01-11-02-42.png">

* I define the class as:

```java
@Entity
@Table(name="student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    // defining constructors
    public Student() {}
    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
    
    // define getters and setters:
	// Getters and Setters here!
    
    // toString implementation
    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\''
                + ", email='" + email + '\'' + '}';
    }
}
```

<br>

# 🧠 3.5 Saving a Java Object with JPA

## 🟦 Student Data Access Object

* Data Access Object (DAO) is a 😱design pattern😱 in which an object is responsible for interfacing with the database:

<img  width="500px" src="screenshots/2023-05-01-11-12-30.png">

* Our Student DAO will have the following methods:

| Methods   		|
| ----------------- |
| `save(...)` ✅  	 		|
| `findById(...)` ✅ 		|
| `findAll(...)` ✅		|
| `findByLastName(...)` ✅ |
| `update(...)` ✅		|
| `delete(...)` ✅		|
| `deleteAll(...)` ✅	|

* This DAO will need a JPA Entity Manager❗

## 🟦 JPA Entity Manager

* The JPA Entity Manager needs a Data Source❗

* The Data Source defines database connection info🤔

* JPA Entity Manager and Data Source are automatically created By Spring Boot - base on application.properties

* We can then inject/autowire the JPA Entity Manager into the Student DAO😱

## 🟦 Plan

### 🟥 StudentDAO and StudentDAOImpl

* We shall define a `StudentDAO` interface which contains a `save()` method which saves a student passed into it✅

* We shall then define an implementation - `StudentDAOImpl` which injects the `EntityManager` and uses it to persist the student object to the database✅

### 🟥 Spring @Transactional Annotation

* 🎃Spring provide the `@Transactional` annotation which will automatically begin and end a transaction for your JPA code🎃 

* We shall use this annotation for our `save()` method in our StudentDAOImpl class✅

### 🟥 Spring @Repository Annotation

* Spring provides the `@Repository` annotation which is a "sub" annotation of `@Component`

* This will register the class its applied as a bean automatically thanks to component scanning✅

* 🎃`@Repository` provides translations for any JDBC related exception🎃

* We shall apply this annotation to our `StudentDAOImpl` class✅

### 🟥 Update the Main App

* We shall inject the `StudentDAO` into the `CommandLineRunner` and write code which creates a Student object and then save it to the database while logging to the console!✅

<hr>

## 👨‍💻 Coding Demo 👨‍💻

* I create a `dao` package:

<img  width="500px" src="screenshots/2023-05-01-12-07-19.png">

* I define a `StudentDAO` interface:

```java
public interface StudentDAO {
    void save(Student student);
}
```

* I implement the interface with `StudentDAOImpl`:

```java
@Repository
public class StudentDAOImpl implements StudentDAO {
    private EntityManager entityManager;
    public StudentDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    @Override
    @Transactional
    public void save(Student student) {
        entityManager.persist(student);
    }
}
```

* ⚠️Without the `@Transactional` annotation, the application will fail when attempting to persist to the database⚠️

* I then update the `CrudDemoApplication` class

```java
@SpringBootApplication
public class CrudDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudDemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
		return runner -> {
			saveStudent(studentDAO);
		};
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
```

* Running the application, I see the following in my console:

<img  width="500px" src="screenshots/2023-05-01-12-11-39.png">

* Looking at MySQL, the student table returns:

<img  width="500px" src="screenshots/2023-05-01-12-12-27.png">




# 🧠 3.1 H1

## 🟦 H2
✅
❗
❌
🤔
⚠️
😊 
😱

* 🎃DEFINITION🎃

IMAGE:    <img  width="500px" src="screenshots/2023-03-27-18-46-20.png">


