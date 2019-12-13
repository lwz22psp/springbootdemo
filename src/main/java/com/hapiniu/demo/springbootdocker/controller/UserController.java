package com.hapiniu.demo.springbootdocker.controller;

import com.alibaba.fastjson.JSON;
import com.hapiniu.demo.springbootdocker.bo.LxmUserBo;
import com.hapiniu.demo.springbootdocker.model.LxmUserModel;
import com.hapiniu.demo.springbootdocker.pojo.*;
import com.hapiniu.demo.springbootdocker.service.AuthService;
import com.hapiniu.demo.springbootdocker.util.MD5;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/user")
@Api(value = "user")
public class UserController {
    @Resource(name = "lxmUserTokenRedisTemplate")
    private RedisTemplate<String, LxmUserModel> redisTemplate;
    private LxmUserBo lxmUserBo;
    private AuthService authService;

    @Value("${regis.code}")
    private String env;

    @Autowired
    public void setLxmUserBo(LxmUserBo lxmUserBo) {
        this.lxmUserBo = lxmUserBo;
    }

    @Autowired
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    @ApiOperation(value = "用户注册", notes = "用户注册")
    public LxmUserRegistResponse regist(@RequestBody LxmUserRegistRequest request) {
        LxmUserRegistResponse resp = new LxmUserRegistResponse();
        if (env.equals(request.getRegistCode())) {
            if (request.getUserName() == null || request.getUserName().isEmpty()) {
                resp.error("用户名不能为空");
            } else if (request.getUserPwd() == null || request.getUserPwd().isEmpty()) {
                resp.error("密码不能为空");
            } else if (lxmUserBo.verifyUsrName(request.getUserName())
                    || lxmUserBo.verifyUsrName(request.getNickName())
                    || lxmUserBo.verifyUsrName(request.getEmail())
                    || lxmUserBo.verifyUsrName(request.getPhone())) {
                resp.error("用户名已注册");
            } else {
                LxmUserModel model = new LxmUserModel(0, request.getUserName(), MD5.eccrypt(request.getUserPwd()), request.getPhone(), request.getEmail(), request.getNickName(), null, null);
                lxmUserBo.addLxmUser(model);
                String token = authService.login(request.getUserName(), request.getUserPwd());
                resp.setToken(token);
                resp.success();
            }
        } else {
            resp.error("邀请码错误");
        }
        return resp;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "用户登陆", notes = "用户登陆")
    public LxmUserLoginResponse login(@RequestBody LxmUserLoginRequest request) {
        LxmUserLoginResponse resp = new LxmUserLoginResponse();
        try{
            String token = authService.login(request.getUserName(), request.getUserPwd());
            resp.setToken(token);
            resp.success();
        }catch (Exception e){
            resp.error("用户名密码错误");
        }
        return resp;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ApiOperation(value = "用户注销", notes = "用户注销")
    public LxmUserLogoutResponse logout(@RequestBody LxmUserLogoutRequest request) {
        LxmUserLogoutResponse response = new LxmUserLogoutResponse();
        try {
            //logout(request.getHeader().getToken());
            response.success();
        } catch (Exception e) {
            response.error(ExceptionUtils.getFullStackTrace(e));
        }
        return response;
    }

    @RequestMapping(value = "/verifyToken", method = RequestMethod.POST)
    @ApiOperation(value = "通过token获取用户信息", notes = "通过token获取用户信息")
    public GetLxmUserResponse verifyToken(@RequestBody GetLxmUserRequest request) {
        GetLxmUserResponse resp = new GetLxmUserResponse();
        try {
            resp.setData(authService.getUser());
            resp.success();
        } catch (Exception e) {
            resp.error(e.getMessage());
        }
        return resp;
    }

    @RequestMapping(value = "/searchUser", method = RequestMethod.POST)
    @ApiOperation(value = "查询用户", notes = "查询用户")
    public SearchLxmUserResponse searchUser(@RequestBody SearchLxmUserRequest request) {
        SearchLxmUserResponse resp = new SearchLxmUserResponse();
        try {
            resp.setData(lxmUserBo.lstLxmUserModel(request.getSearchParam(), request.getPageInfo()));
            resp.setPageInfo(request.getPageInfo());
            resp.success();
        } catch (Exception e) {
            resp.error(e.getMessage());
        }
        return resp;
    }


    private String login(String userName, String userPwd) {
        int uid = lxmUserBo.login(userName, userPwd);
        if (uid == 0) {
            return "";
        } else {
            String uuid = UUID.randomUUID().toString();
            LxmUserModel model = new LxmUserModel();
            model.setUserId(uid);
            redisTemplate.opsForHash().put("user", uuid, model);
            redisTemplate.expire(uuid, 10, TimeUnit.DAYS);
            return uuid;
        }
    }

    private void logout(String token) {
        if (token != null && !token.isEmpty()) {
            redisTemplate.opsForHash().delete("user", token);
        }
    }

//    @RequestMapping(value = "/test", method = RequestMethod.POST)

}
