package com.hapiniu.demo.springbootdocker.pojo;

import com.hapiniu.demo.springbootdocker.pojo.common.ResponseBaseInfo;
import lombok.Data;

/**
 * @author dark
 **/
@Data
public class WechatMessageResponse extends ResponseBaseInfo {
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
}
