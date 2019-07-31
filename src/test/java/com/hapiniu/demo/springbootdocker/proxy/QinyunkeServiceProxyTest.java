package com.hapiniu.demo.springbootdocker.proxy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author dark
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class QinyunkeServiceProxyTest {
    @Autowired
    private QinyunkeServiceProxy proxy;

    @Test
    public void cheatTest() {
         proxy.cheat("free", "0", "你好");
    }
}
