<link rel="stylesheet" href="../style.css" />

# 🟪 Section 4 - Spring Boot 3 - REST CRUD APIs



# 🧠 4.1 Introduction

In this section, we will be doing the following:

* Create REST APIs/Web Services with Spring ✅

* Discuss REST Concepts, JSON, HTTP messaging ✅

* Install Postman (REST Client Tool) ✅

* Develop REST APIs using `@RestController` ✅

* Build CRUD Interface to the database with Spring REST ✅

This will act as an introduction to Spring REST developmebt

<br>

# 🧠 4.2 REST Services

## 🟦 Coding scenario

* Business Problem: build a client an app which produces weather report for the city. The weather data is retrieved from an external service

* The application will have the following architecture:

    <img  width="500px" src="screenshots/2023-04-25-11-43-33.png">

* Questions include how will we connect to weather service 🤔 what programming language 🤔 what data format 🤔

## 🟦 Solution! 

* 😊 We can connect to the weather service using REST API calls over HTTP 😊

* 😊 REST is **language independent**, so we could use any programming language for the app and server! 😊

* 😊 We can use JSON but REST applications can yuse ANY data format 😊

## 🟦 Weather Service

* We can use a Weather service API using [openweathermap.org](www.openweathermap.org)

* We can make calls to the API using a city name as seen in its documentation:

```
api.openweathermap.org/data/2.5/weather?q={cityName}
api.openweathermap.org/data/2.5/weather?q={cityName},{countryCode}
```
## 🟦 Weather Service - Response

* The weather service responds in form of JSON. This would have the following format:

```json
{
    ...
    "temp": 14,
    "temp-min": 11,
    "temp-max": 11,
    "name": "London",
    ...
}
```

* Since the weather service returns data in a language format, we could create multiple client apps using Spring MVC, C#, iPhone... 😱

* REST APIs go under multiple names including RESTful web services, Rest services, ... etc


<br>

# 🧠 4.3 Spring Boot REST HTTP Basics

## 🟦 HTTP Methods

* Most common use of REST is over HTTP, so we can leverage HTTP methods for CRUD operation

| HTTP Method | CRUD Operation |
|-------------|----------------|
| `POST`      | Create a new entity |
| `GET`       | Get a list of entities or a single entity |
| `PUT`       | Update an existing entity |
| `DELETE`    | Delete an existing entity |

* We will send a HTTP Request Message to the server from the app, the server then sends a HTTP Response Message

## 🟦 HTTP Request Message

* A HTTP Request message consists of:

1) Request Line - the HTTP command

2) Header variables - metadata of request

3) Message body - content of the message (this can be in the form of JSON)

## 🟦 HTTP Response Message

* The Response Message consists of:

1) Response line: server protocol/status code

2) Header variables: metadata of response

3) Message body: contents of the message

* The HTTP Response Status codes consist of:

|  Code Range | Description |
|------------|--------------|
| 100-199    | Informational |
| 200-299    | Success      |
| 300-399    | Redirection |
| 400-499    | Client Error |
| 500-599    | Server Error |

## 🟦 MIME Content Types

* MIME - Multipurpose Internet Mail Extension, desribes the messagee format.

* The syntax for this is `type/sub-type`, e.g. `text/html`, `text/plain` etc


## 🟦 Client Tool

* We need a tool to sent HTTP requests to the HTTP REST Service❗ There are multiple client tools available like Postman, Curl, ...

* This course will use Postman!!! I download Postman from [here](https://www.postman.com/downloads/)

<br>

# 🧠 4.4 Postman Demo

* I open a new tab in Postman and see this:

  <img  width="600px" src="screenshots/2023-04-25-13-42-13.png">

* I visit **JSONPlaceholder** ([link](https://jsonplaceholder.typicode.com/)), which has URLs which return dummy JSON data

* I use this URL which returns some users JSON: [https://jsonplaceholder.typicode.com/users](https://jsonplaceholder.typicode.com/users)

* I make a GET request using the above link and see a list of JSON objects in Postman:

    <img  width="600px" src="screenshots/2023-04-25-13-59-14.png">

<br>



# 🧠 4.5 Spring REST Controller

## 🟦 Coding Scenario

* We want to write a REST service such that when it gets a request for `/test/hello`, it sends a response to the client with `Hello World!`

* We can define a REST Controller on a class:

```java
@RestController
@RequestMapping("/test")
public class DemoRestController {

    @GetMapping("/hello")
    public String sayHello(){
        return "Hello World!";a
    }
}
```

* We can test our web service using the browser or Postman - Postman would be the best option when you have more complicated requests, have authentication, ... 

## 🟦 Coding Demo

* We need the following dependency to create a RESTful service:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

* I go to the Spring Initializr websit and setup the project:

    <img  width="500px" src="screenshots/2023-04-25-14-27-18.png">


* I create a new project [here](/Section%203%20-%20REST%20CRUD%20APIs/Demo%2014%20-%20Spring%20Rest%20Controller/demo-09-rest-crud/) and create a class in this package:

    <img  width="400px" src="screenshots/2023-04-25-14-31-06.png">

* I write the class as:

```java
@RestController
@RequestMapping("/test")
public class DemoRestController {

    @GetMapping("/hello")
    public String sayHello(){
        return "Hello World!";
    }
}
```

* I run the Spring application and navigate to the endpoint:

    <img  width="300px" src="screenshots/2023-04-25-14-37-12.png">

* The app works as expected!



<br>



# 🧠 4.6 Java JSON Data Binding

## 🟦 What is Data binding?

* 🎃Data binding is the process of converting JSON data to Java POJO (and reverse)🎃

* This is also know as serialisation/deserialisation or Mapping

* Spring used the Jackson Project which handles the databinding by calling the appropiate getter/setter method:

![](screenshots/2023-05-19-11-04-29.png)

* When mapping from the JSON, Jackson calls the setX() method on the class - it does not access properties directly!

* When mapping from the JSON, Jackson calls the getX() method on the class

* Spring automatically handles Jackson Integration!

<br>



# 🧠 4.7 Spring Boot REST POJO

* We shall create a REST endpoint `/api/students/` which returns a list of students

* We will need to convert the Java List into a JSON. Spring Boot automatically converts the Java POJO as JSON using Jackson

## 👨‍💻 Coding Demo 👨‍💻

* I defined a `Student` class as:

```java
@Component
public class Student {

    private String firstName;
    private String lastName;

    public Student(){}

    public Student(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Student{" + "firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + '}';
    }
}
```

* I created a new end-point in my `DemoRestController`:

```java
@RestController
@RequestMapping("/test")
public class DemoRestController {

    @GetMapping("/hello")
    public String sayHello(){
        return "Hello World!";
    }

    @GetMapping("students")
    public List<Student> getStudents(){
        return Arrays.asList(new Student("Shiv", "Kumar"), new Student("Sammy", "Ling"));
    }
}
```

* Opening up my browser, I get the following:

![](screenshots/2023-05-19-12-06-35.png)

* I realise that it's not working because I have no setters/getters😱 

* After adding the setters/getters, the app starts working:

```java
@Component
public class Student {
    private String firstName;
    private String lastName;
    public Student(){}
    public Student(String firstName, String lastName) {
        this.firstName = firstName; this.lastName = lastName; }
    public String getFirstName() {
        return firstName; }
    public void setFirstName(String firstName) {
        this.firstName = firstName; }
    public String getLastName() {
        return lastName; }
    public void setLastName(String lastName) {
        this.lastName = lastName; }
    @Override
    public String toString() {
        return "Student{" + "firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + '}'; }
}
```

![](screenshots/2023-05-19-12-09-50.png)



<br>



# 🧠 4.8 Spring Boot REST Path Variables

* We can use refactor our code, so that we are not recreating a list of students over and over again. We create a private field in our `DemoRestController` and created a method which initialises this field:

```java
@RestController
@RequestMapping("/test")
public class DemoRestController {

    private List<Student> theStudents;

    @PostConstruct
    public void loadDate() {
        theStudents = Arrays.asList(
                new Student("Shiv", "Kumar"),
                new Student("Sammy", "Ling"),
                new Student("Rohan", "Verma"));
    }
    @GetMapping("/hello")
    public String sayHello(){
        return "Hello World!";
    }
    @GetMapping("students")
    public List<Student> getStudents(){
        return theStudents;
    }
}
```

* I used the `@PostConstruct` annotation so that the method is only executed once after the class has been constructed!

* We can use path variables which let us extract variables from the URL! Suppose we wanted to return a specific student.

* I create a Get mapping in the `DemoRestController`:

```java
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId){
        return theStudents.get(studentId);
    }
```

* The path variable works:

![](screenshots/2023-05-19-12-22-52.png)

* Suppose we make a call which exceeds the index of the List:

![](screenshots/2023-05-19-12-23-57.png)

## 👨‍💻 Coding Demo 👨‍💻

* Our Rest controller instantiates a list of Students each time the /students endpoint is called:

```java
@RestController
@RequestMapping("/api")
public class StudentRestController {
    // define end point for students
    @GetMapping("/students")
    public List<Student> getStudents() {
        return Arrays.asList(
                new Student("Shiv", "Kumar"),
                new Student("Rohan", "Verma")
        );
    }
}
```

* I will utitlise the `@PostConstruct` annotation and use it to initialise the list exactly once! I create a private field for the students which will get initialised:

```java
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
}
```

* I define a new endpoint which will return the student using index:

```java
// define endpoint for "/students/{studentId}" using index
@GetMapping("/students/{studentId}")
public Student getStudentById(@PathVariable int studentId){
    return studentsList.get(studentId);
}
```

* And it works!

![](screenshots/2023-07-12-11-35-42.png)

* If we try a URL which exceeds the bounds of the array, then we get the following error in postman:

![](screenshots/2023-07-12-11-38-56.png)


<br>


# 🧠 4.9 Spring Boot Exception Handling

* We will create a custom error response class, custom Runtime Exception, update the REST service to handle if student is not found.

* The response class will be sent to client as JSON

* We will define a `StudentNotFoundException` which extends Runtime Exception

<br>

## 👨‍💻 Coding Demo 👨‍💻

* I create `StudentErrorResponse` class here

![](screenshots/2023-07-12-11-51-24.png)

* I define its fields, constructors:

```java
public class StudentErrorResponse {
    private int status;
    private String message;
    private long timeStamp;
    public StudentErrorResponse() { }
    public StudentErrorResponse(int status, String message, long timeStamp) {
        this.status = status;
        this.message = message;
        this.timeStamp = timeStamp;
    }
    // getters and setters
}
```

* I create the `StudentNotFoundException` class in this package:

![](screenshots/2023-07-12-11-56-27.png)

* ..and define it as:

```java
public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(String message){
        super(message);
    }
}
```

* I update the `StudentRestController`'s `getStudentById()` method to:

```java
@GetMapping("/students/{studentId}")
public List<Student> getStudentById(@PathVariable int studentId) {
    if (studentId>=studentsList.size() || studentId<0) {
        throw new StudentNotFoundException("This studentId: "+studentId+ " does not exist");
    }
    return studentsList.get(studentId);
}
```

* Within the StudentRestController, I define another method for handling the message:

```java
@ExceptionHandler
public String handleException(StudentNotFoundException exc) {
    return exc.getMessage();
}
```

* Now when I run the application:

![](screenshots/2023-07-14-15-17-58.png)

* I update the exception handler:

```java
public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc) {
    StudentErrorResponse response = new StudentErrorResponse();
    response.setMessage(exc.getMessage());
    response.setStatus(HttpStatus.NOT_FOUND.value());
    response.setTimeStamp(System.currentTimeMillis());
    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
}
```

* Now we see a nice response:

![](screenshtots/2023-07-14-15-28-13.png)

* If we type a parameter which cannot be binded to an `int`, then we get the following:

![](screenshots/023-07-14-15-30-43.png)

* Looking at the logs in the console:

![](screenshots/2023-07-14-15-32-08.png)

* We shall now look at how to create a "catch-all" exception handler to deal with these edge cases:

```java
// handling more general exceptions:
@ExceptionHandler
public ResponseEntity<StudentErrorResponse> handleException(Exception exc) {
    // we are still using the StudentErrorResponse:
    StudentErrorResponse response = new StudentErrorResponse();
    response.setStatus(HttpStatus.BAD_REQUEST.value());
    response.setMessage(exc.getMessage());
    response.setTimeStamp(System.currentTimeMillis());
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
}
```

* Now when we use a random String as the parameter, we get a nice error response:

![](screenshots/2023-07-14-15-39-32.png)


<hr>

# 🧠 4.10 Global Exception Handling

* We've only applied exception handler to a specific REST controller.

* We need to create a central exception handler which can be used across multiple controllers.

* We use `@ControllerAdvice` from Spring to pre-process reuests to controller. This will then the `@ControllerAdvice` will be used to return the response.

<br>

## 👨‍💻 Coding Demo 👨‍💻

* I create a `StudentRestExceptionHandler` class in the `rest` package:

![](screenshots/2023-07-14-15-51-13.png)

* I cut the exception handling methods from `StudentRestController` into my `StudentRestExceptionHandler`:

```java
@ControllerAdvice
public class StudentRestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc) {
        StudentErrorResponse response = new StudentErrorResponse();
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setMessage(exc.getMessage());
        response.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // handling more general exceptions:
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception exc) {
        // we are still using the StudentErrorResponse:
        StudentErrorResponse response = new StudentErrorResponse();
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage(exc.getMessage());
        response.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
```

* I rerun my application, and it all works!!!


# 🧠 4.11 Spring Boot REST Project Setup

*  I create a new REST API project.
* View the repo for this project [here](https://github.com/shivkumar98/Spring-Boot-REST-Project)