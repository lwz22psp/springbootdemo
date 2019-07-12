package com.hapiniu.demo.springbootdocker.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dark
 **/
@Data
@NoArgsConstructor
public class WechatMessageModel {
    private String input;
    private String output;

    public WechatMessageModel(String input) {
        this.input = input;
    }

}
