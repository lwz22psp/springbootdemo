package com.hapiniu.demo.springbootdocker.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Api(value = "test")
public class HomeController {
    @RequestMapping(value = "/",method = RequestMethod.GET)
    @ApiOperation(value = "获取helloWorld", notes = "简单SpringMVC请求" )
    public String homeMessage() {
        return "老西门瞎唔搞系统";
    }
}
