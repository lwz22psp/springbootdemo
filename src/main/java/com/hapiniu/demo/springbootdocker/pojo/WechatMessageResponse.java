package com.hapiniu.demo.springbootdocker.pojo;

import com.hapiniu.demo.springbootdocker.pojo.common.ResponseBaseInfo;
import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author dark
 **/
@Data
@XmlRootElement(name = "xml")
public class WechatMessageResponse extends ResponseBaseInfo implements Serializable {
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
