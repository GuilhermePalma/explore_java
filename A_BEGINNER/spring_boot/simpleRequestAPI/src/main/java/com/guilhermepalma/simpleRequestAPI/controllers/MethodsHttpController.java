package com.guilhermepalma.simpleRequestAPI.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = {"/httpmethods", "/httpMethods"})
public class MethodsHttpController {

    @GetMapping
    public String get() {
        return "This is a GET Request";
    }

    @PostMapping
    public String post() {
        return "This is a POST Request";
    }

    @PutMapping
    public String put() {
        return "This is a PUT Request";
    }

    @PatchMapping
    public String patch() {
        return "This is a PATCH Request";
    }

    @DeleteMapping
    public String delete() {
        return "This is a DELETE Request";
    }
}
