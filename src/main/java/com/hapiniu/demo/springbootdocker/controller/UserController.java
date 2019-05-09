package com.hapiniu.demo.springbootdocker.controller;

import com.alibaba.fastjson.JSON;
import com.hapiniu.demo.springbootdocker.bo.LxmUserBo;
import com.hapiniu.demo.springbootdocker.model.LxmUserModel;
import com.hapiniu.demo.springbootdocker.pojo.*;
import com.hapiniu.demo.springbootdocker.util.MD5;
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
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/user")
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
                    ||lxmUserBo.verifyUsrName(request.getNickName())
                    ||lxmUserBo.verifyUsrName(request.getEmail())
                    ||lxmUserBo.verifyUsrName(request.getPhone())) {
                resp.error("用户名已注册");
            } else {
                LxmUserModel model =new LxmUserModel(0,request.getUserName(), MD5.eccrypt(request.getUserPwd()),request.getPhone(),request.getEmail(),request.getNickName());
                lxmUserBo.addLxmUser(model);
                String token =  login(request.getUserName(),request.getUserPwd());
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
        String token = login(request.getUserName(),request.getUserPwd());
        if("".equals(token)){
            resp.error("用户名密码错误");
        }else{
            resp.setToken(token);
            resp.success();
        }
        return resp;
    }

    @RequestMapping(value = "/verifyToken", method = RequestMethod.POST)
    @ApiOperation(value = "通过token获取用户信息", notes = "通过token获取用户信息")
    public GetLxmUserResponse verifyToken(@RequestBody GetLxmUserRequest request) {
        GetLxmUserResponse resp = new GetLxmUserResponse();
        try{
            String token = request.getHeader().getToken();
            if("".equals(token)||!redisTemplate.opsForHash().hasKey("user", request.getHeader().getToken())){
                resp.error("token错误");
            }else{
                Object redisObject = redisTemplate.opsForHash().get("user", request.getHeader().getToken());
                int userId = JSON.parseObject(JSON.toJSONString(redisObject),LxmUserModel.class).getUserId() ;
                resp.setData(lxmUserBo.getLxmUserModelById(userId));
                resp.success();
            }
        }catch (Exception e){
            resp.error(e.getMessage());
        }

        return resp;
    }


    private String login(String userName,String userPwd){
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

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @ApiOperation(value = "测试", notes = "测试")
    public TestResponse test(@RequestBody TestRequest request) {
        TestResponse resp = new TestResponse();
        if (redisTemplate.opsForHash().hasKey("user", request.getHeader().getToken())) {
            resp.success();
        } else {
            resp.error("");
        }
        return resp;
    }
}
