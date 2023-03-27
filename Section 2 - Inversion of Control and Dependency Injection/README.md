<link rel="stylesheet" href="../style.css" />

# 🟪 Section 2: Spring Boot 3 - Inversion of Control and Dependency Injection

# 🧠 2.1 Inversion of Conntrol

## 🟦 What is Inversion of Control

* 🎃This is the approach of outsourcing constructiona and management of objects🎃

## 🟦 Coding scenario

* Suppose we have an app which calls thge `getDailyWorkout()` from a `CricketCoach` class

* We want the app to be **configurable** and eaily change the coach for another support (e.g. Baseball, Hockey ... )

* The ideal solution would be my app calling an Object Factory! Spring provides us with this via the Spring Container

    <img  width="300px" src="screenshots/2023-03-27-18-46-20.png">

## 🟦 Spring Container

* The Spring Container has two roles:

1) Create and Manager Objects (Inversion of Control) ✅

2) Injecting object dependencies (Dependency Injection) ✅

## 🟦 Configuring Spring Container

* We can configure the Spring container in 3 ways:

1) XML Configuration (outdated) ❌

2) Java Annotations ✅

3) Java Source Code ✅

<br>


# 🧠 2.2 Spring Dependency Injections

* The dependency inversion principle states **the client delegates to another object the resposibility of providing its dependencies**

## 🟦 Demo Example

* We have a `DemoController` which wants to use the `Coach` class - the dependency!👀

    <img  width="300px" src="screenshots/2023-03-27-19-01-47.png">

* We shall **INJECT THE DEPENDENCY** into the controller

## 🟦 Injection Types

* We can inject a dependency in two ways:

1) Constructor Injection - recommended for required dependencies

2) Setter Injection - recommended for optional dependencies

## 🟦 Spring Autowiring

* Spring will look look for matches by type and inject automatically

## 🟦 Autowiring Example

* Spring will scan for `@Components`

* The `@Component` annotation makes a Java class into a Spring Bean

## 🖥️ Code Demo 🖥️

### Steps:

* We shall define an interface `Coach` with a `getDailyWorout()` method

* We will then create an implementation which overrides the `getDailyWorkout()` method

* We weill make a `DemoController` coach with a private `Coach` field.

* We will then have a constructor with the `@Autowired` annotation

* Finally, we expose an endpoint to display the `getDailyWorkout()` method

### Process

* I create a new Spring project on Spring initializr:


<img  width="300px" src="screenshots/2023-03-27-19-16-52.png">

* I define a `Coach` interface:

```java
    public interface Coach {
        String getDailyWorkout();
    }
```

* I implement the interface with a `CricketCoach` class:

```java
@Component
public class CricketCoach implements Coach {

    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 15 mins";
    }
}
```

* Note the use of `@Component`!

* I create a `DemoController`:

```java
@RestController
public class DemoController {

    private Coach coach;

    // depedency injection through constructor:
    @Autowired
    public DemoController(Coach theCoach){
        this.coach = theCoach;
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout(){
        return coach.getDailyWorkout();
    }
}
```

* I run the Spring application:
    
    <img  width="300px" src="screenshots/2023-03-27-19-23-19.png">