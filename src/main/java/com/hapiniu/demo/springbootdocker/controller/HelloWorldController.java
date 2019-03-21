package com.hapiniu.demo.springbootdocker.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @RequestMapping("/hello")
    public String hello() {
        return "<h1>Hello Spring-Boot Maven Docker</h1>";
    }
}
