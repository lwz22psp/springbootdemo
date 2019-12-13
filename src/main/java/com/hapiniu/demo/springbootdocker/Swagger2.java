package com.hapiniu.demo.springbootdocker;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class Swagger2 {
    @Bean
    public Docket createRestApi() {
        // 添加请求参数，我们这里把token作为请求头部参数传入后端
        ParameterBuilder parameterBuilder = new ParameterBuilder();
        List<Parameter> parameters = new ArrayList<Parameter>();
        parameterBuilder.name("Authorization").description("令牌").modelRef(new ModelRef("string")).parameterType("header")
                .required(false).build();
        parameters.add(parameterBuilder.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hapiniu.demo.springbootdocker"))
                .paths(PathSelectors.any())
                .build().globalOperationParameters(parameters);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("老西门应用  APIs")
                .description("--------------------------------")
                .termsOfServiceUrl("https://github.com/lwz22psp/springbootdemo")
                .contact(new Contact("hapiniu","https://github.com/lwz22psp","lwz22psp@hotmail.com"))
                .version("0.1.1")
                .build();
    }
}
