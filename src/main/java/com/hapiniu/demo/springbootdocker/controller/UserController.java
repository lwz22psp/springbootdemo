package com.hapiniu.demo.springbootdocker.controller;

import com.hapiniu.demo.springbootdocker.bo.LxmUserBo;
import com.hapiniu.demo.springbootdocker.model.LxmUserModel;
import com.hapiniu.demo.springbootdocker.pojo.LxmUserRegistRequest;
import com.hapiniu.demo.springbootdocker.pojo.LxmUserRegistResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@Api(value = "user")
public class UserController {
    @Resource(name = "lxmUserTokenRedisTemplate")
    private RedisTemplate<String, LxmUserModel> redisTemplate;
    private LxmUserBo lxmUserBo;

    @Value("${regis.code}")
    private String env;

    @Autowired
    public void setLxmUserBo(LxmUserBo lxmUserBo) {
        this.lxmUserBo = lxmUserBo;
    }

    @RequestMapping(value = "/regist", method = RequestMethod.PUT)
    @ApiOperation(value = "用户注册", notes = "用户注册")
    public LxmUserRegistResponse addUser(@RequestBody LxmUserRegistRequest request) {
        LxmUserRegistResponse resp = new LxmUserRegistResponse();
        if (env.equals(request.getRegistCode())) {
            if (request.getUserName() == null || request.getUserName().isEmpty()) {
                resp.error("用户名不能为空");
            } else if (request.getUserPwd() == null || request.getUserPwd().isEmpty()) {
                resp.error("密码不能为空");
            } else if (!lxmUserBo.verifyUsrName(request.getUserName())) {
                resp.error("用户名已注册");
            } else {
                lxmUserBo.addLxmUser(request.getUserName(), request.getUserPwd());
                resp.success();
            }
        } else {
            resp.error("邀请码错误");
        }
        return resp;
    }


//    @RequestMapping(value = "/temp", method = RequestMethod.GET)
//    @ApiOperation(value = "获取helloWorld", notes = "简单SpringMVC请求")
//    public String homeMessage() {
//        String uuid = UUID.randomUUID().toString();
//        LxmUserModel model = new LxmUserModel();
//        model.setUserId(123);
//        redisTemplate.opsForHash().put("usrToken", uuid, model);
//        return uuid;
//    }
//
//
//    @RequestMapping(value = "/verifyToken", method = RequestMethod.POST)
//    @ApiOperation(value = "verifyToken", notes = "verifyToken")
//    public Boolean verifyToken(@RequestBody String token) {
//        return redisTemplate.opsForHash().hasKey("usrToken", token);
//    }
}
