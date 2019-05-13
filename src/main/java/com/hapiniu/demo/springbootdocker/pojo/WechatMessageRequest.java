package com.hapiniu.demo.springbootdocker.pojo;

import com.hapiniu.demo.springbootdocker.pojo.common.RequestBaseInfo;
import lombok.Data;

@Data
public class WechatMessageRequest extends RequestBaseInfo {

    /**
     * 开发者微信号
     */
    private String toUserName;

    /**
     * 发送方帐号（一个OpenID）
     */
    private String fromUserName;

    private Integer createTime;

    private String msgType;

    private String content;
    private Long msgId;
}
