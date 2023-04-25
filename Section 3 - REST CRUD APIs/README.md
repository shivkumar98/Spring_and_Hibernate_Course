<link rel="stylesheet" href="../style.css" />

# 🟪 Section 3 - Spring Boot 3 - REST CRUD APIs



# 🧠 3.1 Introduction

In this section, we will be doing the following:

* Create REST APIs/Web Services with Spring ✅

* Discuss REST Concepts, JSON, HTTP messaging ✅

* Install Postman (REST Client Tool) ✅

* Develop REST APIs using `@RestController` ✅

* Build CRUD Interface to the database with Spring REST ✅

This will act as an introduction to Spring REST developmebt

<br>

# 🧠 3.2 REST Services

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

# 🧠 3.3 Spring Boot REST HTTP Basics

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

---



* 🎃DEFINITION🎃

## 🟦 H2

IMAGE:    <img  width="300px" src="screenshots/2023-03-27-18-46-20.png">

CHECKBOX ✅

CROSS  ❌
