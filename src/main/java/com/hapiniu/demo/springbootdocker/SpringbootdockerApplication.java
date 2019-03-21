package com.hapiniu.demo.springbootdocker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class SpringbootdockerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootdockerApplication.class, args);
    }
    @RequestMapping("/hello")
    public String hello() {
        return "<h1>Hello Spring-Boot Maven Docker</h1>";
    }
}
