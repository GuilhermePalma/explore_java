package com.guilhermepalma.simpleRequestAPI.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calculate")
public class CalculateController {

    @GetMapping("sum/{numberOne}/{numberTwo}")
    public double sumNumbers(@PathVariable double numberOne, @PathVariable double numberTwo) {
        return numberOne + numberTwo;
    }

    @GetMapping("sub")
    public double subtractNumbers(@RequestParam double numberOne, @RequestParam double numberTwo) {
        return numberOne - numberTwo;
    }
}
