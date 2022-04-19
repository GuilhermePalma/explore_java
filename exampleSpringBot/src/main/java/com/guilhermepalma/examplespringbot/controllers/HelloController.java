package com.guilhermepalma.examplespringbot.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping
    public String hello() {
        return "Hello Spring Bot";
    }

    @GetMapping(path = {"/get", "/getmapping"})
    public String helloGet() {
        return "Hello Get Mapping of Spring Bot";
    }

    @RequestMapping(path = {"/mapping", "/requestMapping"}, method = RequestMethod.GET)
    public String helloRequestMapping() {
        return "Hello Request Mapping of Spring Bot";
    }

}
