package com.luv2code.springboot.demo.mycoolapp.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {

    // injecting properties for coach.name and team.name
    @Value("${coach.name}")
    private String coachName;

    @Value("${team.name}")
    private String teamName;

    // new end points shows teamName and coachName injected values
    @GetMapping("/teaminfo")
    public String getTeamInfo(){
        return "Coach: "+ coachName + ", Team: "+ teamName;
    }



    @GetMapping("/")
    public String sayHello(){
        return "Hello World!";
    }

    // exposing a new endpoint for "workout"
    @GetMapping("/workout")
    public String getDailyWorkOut(){
        return "Run a hard 5k!";
    }

    // exposing a new endpoint for "fortune"
    @GetMapping("/fortune")
    public String getDailyFortune(){
        return "Today is your lucky day!";
    }
}

