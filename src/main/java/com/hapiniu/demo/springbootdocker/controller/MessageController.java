package com.hapiniu.demo.springbootdocker.controller;

import com.alibaba.fastjson.JSON;
import com.hapiniu.demo.springbootdocker.pojo.WechatMessageRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

/**
 * @author dark
 **/
@RestController
@RequestMapping("/api/message")
@Api(value = "message")
public class MessageController {

    static Logger log = Logger.getLogger(MessageController.class.getName());
    @Value("${regis.code}")
    private String env;

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    //@RequestMapping(value = "/test",method = RequestMethod.GET)
    @ApiOperation(value = "respMsg", notes = "respMsg")
    public String respMsg(@RequestBody WechatMessageRequest request) {
        //WechatMessageResponse resp = new WechatMessageResponse();
        try {
            log.info("Get request url /api/message/test: " + JSON.toJSONString(request));
            //resp.setContent("test");
            return "success";
        } catch (Exception e) {
            return "success";
        }
    }


}
