package com.hapiniu.demo.springbootdocker.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "qinyunkeServiceProxy", url = "http://api.qingyunke.com/api.php")
public interface QinyunkeServiceProxy {
    @RequestMapping(value = "/", method = RequestMethod.GET, consumes = "application/json;charset=UTF-8")
    String cheat(@RequestParam String key, @RequestParam String appid, @RequestParam String msg);
}
