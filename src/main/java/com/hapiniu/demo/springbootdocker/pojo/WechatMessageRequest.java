package com.hapiniu.demo.springbootdocker.pojo;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.hapiniu.demo.springbootdocker.pojo.common.RequestBaseInfo;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName ="xml")
public class WechatMessageRequest extends RequestBaseInfo {

    /**
     * 开发者微信号
     */
    @JacksonXmlProperty(localName ="ToUserName")
    private String toUserName;

    /**
     * 发送方帐号（一个OpenID）
     */

    @JacksonXmlProperty(localName ="FromUserName")
    private String fromUserName;

    @JacksonXmlProperty(localName ="CreateTime")
    private Integer createTime;

    @JacksonXmlProperty(localName ="MsgType")
    private String msgType;

    @JacksonXmlProperty(localName ="Content")
    private String content;

    @JacksonXmlProperty(localName ="MsgId")
    private Long msgId;
}
