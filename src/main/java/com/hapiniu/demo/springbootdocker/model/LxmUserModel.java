package com.hapiniu.demo.springbootdocker.model;

import lombok.Data;

@Data
public class LxmUserModel {
    private Integer userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * password
     */
    private String userPwd;
}
