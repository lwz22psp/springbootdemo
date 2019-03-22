package com.hapiniu.demo.springbootdocker.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @RequestMapping("/index")
    public String homeMessage() {
        return "<h1>老西门瞎唔搞系统</h1><div>by hapiniu</div>";
    }
}
