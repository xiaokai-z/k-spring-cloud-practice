package com.xk.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/hello")
@RestController
public class HelloController {
    @GetMapping("/")
    public String hello(@RequestParam(defaultValue = "") String name){
        return "hello "+ name;
    }
}
