package com.hapiniu.demo.springbootdocker.controller;

import com.hapiniu.demo.springbootdocker.bo.LxmUserBo;
import com.hapiniu.demo.springbootdocker.pojo.LxmUserInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Api(value = "user")
public class UserController {
    private LxmUserBo lxmUserBo;

    @Autowired
    public void setLxmUserBo(LxmUserBo lxmUserBo) {
        this.lxmUserBo = lxmUserBo;
    }

    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    @ApiOperation(value = "用户注册", notes = "用户注册")
    public String addUser(@RequestBody LxmUserInfo request) {
        lxmUserBo.addLxmUser(request.getUserName(),request.getUserPwd());

        return "success";
    }
}
