package com.hapiniu.demo.springbootdocker.pojo.common;

import lombok.Data;

/**
 * @author dark
 **/
@Data
public class ResponseBaseInfo {
    private int code;
    private String msg;

    public void success(){
        this.code=200;
    }

    public void error(String errMsg){
        this.code=500;
        this.msg=errMsg;
    }
}
