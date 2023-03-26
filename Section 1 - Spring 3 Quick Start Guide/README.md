<link rel="stylesheet" href="../style.css" />

# 🟪 Section 1: Spring Boot 3 - Quick Start

# 🧠 1.1 Spring Boot

## 🟦 Overview

* Spring Boot simplifies the start up of a spring application. It minimises setup configuration including servers and dependency conflicts

* Has an embedded HTTP server, so we can use Tomcat straight away!

* We can use Spring Initializr to set up out project

## 🟦 Running Spring Boot Apps

* We can run a Spring Boot application standalone using the jar file which is created

* You can also deploy a WAR file to an external server, the WAR file excludes the server configuration.

    <img  width="300px" src="screenshots/2023-03-26-10-50-40.png">

## 🟦 FAQs

1) Does Spring boot replace Spring MVC, Spring Rest

    A: No! Spring uses these depenendencies!

    <img  width="300px" src="screenshots/2023-03-26-10-51-37.png">

2) Is Spring Boot faster?

    A: No!

<hr>

# 🧠 1.2 Sprint Boot Initializr Demo

## 🟦 Maven Solution

* Maven can be used to download all the JAR dependencies we need!

## 🟦 Spring Initiailizr

* I go to the Spring Initiailzr website [here](https://start.spring.io/)

* The dependencies I take is Spring Web.

* I have the following project setup:

    <img  width="500px" src="screenshots/2023-03-26-10-58-33.png">

* I unzip the folder into this directory [here](/Section%201%20-%20Spring%203%20Quick%20Start%20Guide/demo%201/mycoolapp/)

* I import in the project into eclipse!

## 🟦 Code Demo

* I click `File -> Open` and select the POM of the unzipped file. I click the build icon and then `Right Click -> Run 'Mycoolappp...(main)`

```java
    @SpringBootApplication
    public class MycoolappApplication {

        public static void main(String[] args) {
            SpringApplication.run(MycoolappApplication.class, args);
        }

    }
```

* I run the application:

    <img  width="500px" src="screenshots/2023-03-26-11-10-39.png">

* Going to localhost:8080 gives me:

    <img  width="300px" src="screenshots/2023-03-26-11-11-13.png">

<br>

# 🧠 1.3 Creating a REST Controller

* We will create a REST controller, so it will display:
<img  width="300px" src="screenshots/2023-03-26-11-12-30.png">

* The plan is:

<img  width="300px" src="screenshots/2023-03-26-12-40-03.png">

## 🟦 Code Demo

* I define a new package and class:

<img  width="300px" src="screenshots/2023-03-26-12-42-36.png">

* I write the following:

```java
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {

    @GetMapping("/")
    public String sayHello(){
        return "Hello World!";
    }
}
```

* I run my application from the main method. And go to localhost:8080/

<img  width="300px" src="screenshots/2023-03-26-12-44-42.png">

<br>

# 🧠 1.4 Spring Framework Overview

## 🟦 Goals of Spring

* Lightweight development with POJO

* Dependency injection to encourage loose-coupling

* Minimise boiler-plating

* AOP: add functionality such as logging, security and transactions in a declarative manner

* Support for TDD ans mocking objects outside of the container

## 🟦 Data Access Layer

* JDBC - reduces amount of code by 50%

* ORM - objection-relational mapping

* Transaction support on methods

<br>

# 🧠 1.5 Spring Projects

* These are additional modules built on top of the core Spring framework

* There are many projects such as cloud, data, security

<br>

# 🧠 1.6 What is Maven

* Spring Initializr can generate a Maven project for you

## 🟦 What is Maven?

* A popular tool for dependency and build management.

* Let's us download additional dependencies without manual intervention.

* Maven makes the JAR files available during compilation/run

## 🟦 How Maven works:

* Maven will look at your project configuration file

* It will check if your local repo, if it is not present it will download the dependencies from the Maven Central Repo and place it in your local repo!

* Maven will then use your local repo to build the project

    <img  width="300px" src="screenshots/2023-03-26-13-06-34.png">

* Maven willa also download supporting dependencies for the dependencies!

* Maven will handle class/build path for you

<br>

# 🧠 1.7 Maven Project Structure

## 🟦 Project Structure

* Maven uses a standard project structure which encourages uniformity between developers!

* IDEs support Maven out the box, we can also move the project around different IDEs

    <img  width="300px" src="screenshots/2023-03-26-13-09-21.png">

* Java code is stored in src/main/java

* Any web resources are placed in src/main/resources


## 🟦 POM File

* The POM is the Project Object Model file located in root of project! ✅

    <img  width="200px" src="screenshots/2023-03-26-13-19-33.png">

* The POM contains metadata (version, output file type), dependencies and custom task plugins

## 🟦 Simple POM File

<img  width="300px" src="screenshots/2023-03-26-13-21-24.png">

## 🟦 Project Coordinates

* A project coordinate is a way to uniquely identify a project.

* The project coordinate consists of:

    - **Group ID**: the name of organisation or company. E.g. `com.luv2code`

    - **Artifact ID**: the name of the project

    - **Version**: specific release version. E.g. `1.0` or `1.0-SNAPSHOT`

    <img  width="200px" src="screenshots/2023-03-26-13-24-18.png">
