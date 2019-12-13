package com.hapiniu.demo.springbootdocker.entity;

import lombok.Data;

/**
 * @author dark
 **/
@Data
public class LxmUserSearchResult {
    private Integer userId;

    /**
     * 用户名
     */
    private String userName;

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
