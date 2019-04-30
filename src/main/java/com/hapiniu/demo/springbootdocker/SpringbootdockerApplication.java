package com.hapiniu.demo.springbootdocker;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@MapperScan(value = {"com.hapiniu.demo.springbootdocker"})
@EnableEurekaClient
public class SpringbootdockerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootdockerApplication.class, args);
    }

}
