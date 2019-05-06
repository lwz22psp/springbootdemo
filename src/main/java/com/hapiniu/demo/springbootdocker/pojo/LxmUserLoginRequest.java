package com.hapiniu.demo.springbootdocker.pojo;

import com.hapiniu.demo.springbootdocker.pojo.common.RequestBaseInfo;
import lombok.Data;

@Data
public class LxmUserLoginRequest extends RequestBaseInfo {

    /**
     * 用户名
     */
    private String userName;

    /**
     * password
     */
    private String userPwd;

}
