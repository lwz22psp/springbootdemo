package com.hapiniu.demo.springbootdocker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
    /**
     * phone
     */
    private String phone;
    /**
     * email
     */
    private String email;
    /**
     * nickName
     */
    private String nickName;

    private String wechatUnionID;


}
