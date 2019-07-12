package com.hapiniu.demo.springbootdocker.bo;

import com.alibaba.fastjson.JSON;
import com.hapiniu.demo.springbootdocker.model.WechatMessageModel;
import com.hapiniu.demo.springbootdocker.proxy.QinyunkeServiceProxy;
import com.hapiniu.demo.springbootdocker.proxy.model.QinyunkeServiceModel;
import com.hapiniu.demo.springbootdocker.ws.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.logging.Logger;


/**
 * @author dark
 **/
@Service
public class WechatMessageBo {
    private static Logger log = Logger.getLogger(WechatMessageBo.class.getName());
    private static String DEFAULT_REPLAY_STRING = "咕咕咕～";
    private static Integer SUCCESS_RESULT = 0;
    @Value("${regis.code}")
    private String registCode;
    private QinyunkeServiceProxy qinyunkeServiceProxy;

    @Autowired
    public void setQinyunkeServiceProxy(QinyunkeServiceProxy qinyunkeServiceProxy) {
        this.qinyunkeServiceProxy = qinyunkeServiceProxy;
    }

    public WechatMessageModel dealWithMessage(String input) {
        WechatMessageModel wechatMessageModel = new WechatMessageModel(input);
        if (input.contains("注册码") || input.contains("验证码")) {
            wechatMessageModel.setOutput(getRegistCodeHit());
        }
        if ("hapiniu666".equals(input)) {
            wechatMessageModel.setOutput(getRegistCode());
        }
        if (input.contains("#wstest")) {
            wechatMessageModel.setOutput(getDanMu(input));
        }

        //default
        if (wechatMessageModel.getOutput()==null||wechatMessageModel.getOutput().isEmpty()) {
            wechatMessageModel.setOutput(cheatBotResult(input));
        }
        log.info("WechatMessageBo DealWithMessage finish, result: "+JSON.toJSONString(wechatMessageModel));
        return wechatMessageModel;
    }

    private String cheatBotResult(String input) {
        try {
            String t = qinyunkeServiceProxy.cheat("free", "0", input);
            QinyunkeServiceModel model = JSON.parseObject(t, QinyunkeServiceModel.class);
            if (SUCCESS_RESULT.equals(model.getResult())) {
                return model.getContent();
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return DEFAULT_REPLAY_STRING;
    }

    private String getRegistCode() {
        return "恭喜泥～注册码：" + registCode;
    }

    private String getRegistCodeHit() {
        return "请说出神奇小密码～(夸一下哈皮牛 o(￣ヘ￣o＃) )";
    }

    private String getDanMu(String input) {
        if (verifyMessage(input)) {
            try {
                WebSocketServer.sendInfo(input.replace("#wstest", ""), null);
                return "弹幕发送成功，biu～";
            } catch (IOException e) {
                log.info(e.getMessage());
            }
        } else {
            return "弹幕包含违禁词汇，不能发送～ (￣_,￣ )";
        }
        return DEFAULT_REPLAY_STRING;
    }

    private boolean verifyMessage(String msg) {
        return !((msg.contains("hapiniu") || msg.contains("哈皮牛")) && msg.contains("两拨"));
    }

}
