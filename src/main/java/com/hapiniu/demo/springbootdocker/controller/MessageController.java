package com.hapiniu.demo.springbootdocker.controller;

import com.alibaba.fastjson.JSON;
import com.hapiniu.demo.springbootdocker.pojo.WechatMessageRequest;
import com.hapiniu.demo.springbootdocker.pojo.WechatMessageResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dark
 **/
@RestController
@RequestMapping("/api/message")
@Api(value = "message")
public class MessageController {

    @Value("${regis.code}")
    private String env;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ApiOperation(value = "respMsg", notes = "respMsg")
    public String respMsg(@RequestBody WechatMessageRequest request) {
        WechatMessageResponse resp = new WechatMessageResponse();
        try {
            resp.setContent("test");
            resp.setFromUserName(request.getToUserName());
            resp.setToUserName(request.getFromUserName());
            resp.setCreateTime(request.getCreateTime());
            return JSON.toJSONString(resp);

        } catch (Exception e) {
            return "success";
        }

    }
}
