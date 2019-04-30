package com.hapiniu.demo.springbootdocker.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Api(value = "test")
@RefreshScope
public class HomeController {

    @Value("123")
    private String env;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    @ApiOperation(value = "获取helloWorld", notes = "简单SpringMVC请求" )
    public String homeMessage() {
        return env;
    }
}
