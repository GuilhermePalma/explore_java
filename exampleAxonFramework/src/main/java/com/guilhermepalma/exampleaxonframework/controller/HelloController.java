package com.guilhermepalma.exampleAxonFramework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello/")
public class HelloController {

    @GetMapping
    @Operation(summary = "Get a Default Hello")
    @ApiResponses(value = @ApiResponse(responseCode = "200", description = "Say Hello"))
    public String hello() {
        return "Hello";
    }

}
