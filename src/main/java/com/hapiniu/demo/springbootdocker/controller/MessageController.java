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

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

/**
 * @author dark
 **/
@RestController
@RequestMapping("/api/message")
@Api(value = "message")
public class MessageController {

    @Value("${regis.code}")
    private String env;

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @ApiOperation(value = "respMsg", notes = "respMsg")
    public String respMsg(@RequestBody WechatMessageRequest request) {
        WechatMessageResponse resp = new WechatMessageResponse();
        try {
            resp.setContent("test");
            resp.setFromUserName(request.getToUserName());
            resp.setToUserName(request.getFromUserName());
            resp.setCreateTime(request.getCreateTime());
            return jaxbObjectToXML(resp);
        } catch (Exception e) {
            return "success";
        }

    }

    private static String jaxbObjectToXML(WechatMessageResponse customer) {
        String xmlString = "";
        try {
            JAXBContext context = JAXBContext.newInstance(WechatMessageResponse.class);
            Marshaller m = context.createMarshaller();

            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // To format XML

            StringWriter sw = new StringWriter();
            m.marshal(customer, sw);
            xmlString = sw.toString();

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return xmlString;
    }

}
