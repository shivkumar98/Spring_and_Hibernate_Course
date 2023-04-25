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

![](2023-04-25-15-22-13.png)

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

# 🧠 3.1 H1

## 🟦 H2
✅
❌
🤔
😊 
😱

* 🎃DEFINITION🎃

IMAGE:    <img  width="500px" src="screenshots/2023-03-27-18-46-20.png">


