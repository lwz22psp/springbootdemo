package com.hapiniu.demo.springbootdocker.bo;

import com.hapiniu.demo.springbootdocker.proxy.QinyunkeServiceProxy;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author dark
 **/
public class WechatMessageBoTest {
    @Test
    public void dealWithMessageTest() {
        QinyunkeServiceProxy qinyunkeServiceProxy = mock(QinyunkeServiceProxy.class);
        WechatMessageBo bo = new WechatMessageBo();
        bo.setQinyunkeServiceProxy(qinyunkeServiceProxy);
        bo.setRegistCode("test");
        when(qinyunkeServiceProxy.cheat("free", "0", "112")).thenReturn("{\"result\":0,\"content\":\"11\"}");

        //正常聊天机器人
        Assert.assertEquals("11", bo.dealWithMessage("112").getOutput());

        //聊天接口挂了
        when(qinyunkeServiceProxy.cheat("free", "0", "112")).thenReturn("");
        Assert.assertEquals("咕咕咕～", bo.dealWithMessage("112").getOutput());


        //注册码
        Assert.assertEquals("请说出神奇小密码～(夸一下哈皮牛 o(￣ヘ￣o＃) )", bo.dealWithMessage("注册码").getOutput());
        Assert.assertEquals("恭喜泥～注册码：test", bo.dealWithMessage("hapiniu666").getOutput());

        //danmu
        Assert.assertEquals("弹幕包含违禁词汇，不能发送～ (￣_,￣ )", bo.dealWithMessage("#wstest 哈皮牛两拨").getOutput());
        Assert.assertEquals("弹幕发送成功，biu～", bo.dealWithMessage("#wstest 哈皮牛").getOutput());

    }
}
