package com.hapiniu.demo.springbootdocker.pojo;

import com.hapiniu.demo.springbootdocker.pojo.common.RequestBaseInfo;
import lombok.Data;

@Data
public class LxmUserRegistRequest extends RequestBaseInfo {

    /**
     * 用户名
     */
    private String userName;

    /**
     * password
     */
    private String userPwd;

    /**
     * registCode
     */
    private String registCode;

    private String phone;
    private String email;
    private String nickName;
}
