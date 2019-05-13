package com.hapiniu.demo.springbootdocker.controller;

import com.alibaba.fastjson.JSON;
import com.hapiniu.demo.springbootdocker.pojo.WechatMessageRequest;
import com.hapiniu.demo.springbootdocker.pojo.WechatMessageResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.logging.Logger;

/**
 * @author dark
 **/
@RestController
@RequestMapping("/api/message")
@Api(value = "message")
public class MessageController {

    @Value("${regis.code}")
    private String env;

    static Logger log= Logger.getLogger(MessageController.class.getName());

    //@RequestMapping(value = "/test", method = RequestMethod.POST,produces = {MediaType.APPLICATION_XML_VALUE},consumes = { MediaType.APPLICATION_XML_VALUE })
  @RequestMapping(value = "/test",method = RequestMethod.GET)
    @ApiOperation(value = "respMsg", notes = "respMsg")
    public String respMsg(@RequestParam("signature") String signature,
                          @RequestParam("timestamp") String timestamp,
                          @RequestParam("nonce") String nonce,
                          @RequestParam("echostr") String echostr,
                          @RequestParam("token") String token) {
        //WechatMessageResponse resp = new WechatMessageResponse();
        try {
            log.info("Get request url /api/message/test: "+echostr);
            //resp.setContent("test");
            return echostr;
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
