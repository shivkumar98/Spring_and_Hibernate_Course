<link rel="stylesheet" href="../style.css" />

# 🟪 Section 3 - Spring Boot 3 - REST CRUD APIs



# 🧠 2.1 Introduction

In this section, we will be doing the following:

* Create REST APIs/Web Services with Spring ✅

* Discuss REST Concepts, JSON, HTTP messaging ✅

* Install Postman (REST Client Tool) ✅

* Develop REST APIs using `@RestController` ✅

* Build CRUD Interface to the database with Spring REST ✅

This will act as an introduction to Spring REST developmebt

# 🧠 2.2 REST Services

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

---



* 🎃DEFINITION🎃

## 🟦 H2

IMAGE:    <img  width="300px" src="screenshots/2023-03-27-18-46-20.png">

CHECKBOX ✅

CROSS  ❌
