package com.hapiniu.demo.springbootdocker.controller;

import com.alibaba.fastjson.JSON;
import com.hapiniu.demo.springbootdocker.pojo.WechatMessageRequest;
import com.hapiniu.demo.springbootdocker.pojo.WechatMessageResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/test", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE}
    )
    @ApiOperation(value = "respMsg", notes = "respMsg")
    public WechatMessageResponse respMsg(@RequestBody WechatMessageRequest request) {
        WechatMessageResponse resp = new WechatMessageResponse();
        try {
            log.info("Get request url /api/message/test: " + JSON.toJSONString(request));
            resp.setCreateTime(request.getCreateTime());
            resp.setFromUserName(request.getToUserName());
            resp.setToUserName(request.getFromUserName());
            resp.setMsgType(request.getMsgType());
            resp.setContent("test");

        } catch (Exception e) {
            resp.error(e.getMessage());
        }
        return resp;
    }


    //验证微信时候用
    //@RequestMapping(value = "/test",method = RequestMethod.GET)
    public String verify(@RequestParam("signature") String signature,
                         @RequestParam("timestamp") String timestamp,
                         @RequestParam("nonce") String nonce,
                         @RequestParam("echostr") String echostr) {
       return echostr;
    }

}
