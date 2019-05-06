package com.hapiniu.demo.springbootdocker.pojo;

import com.hapiniu.demo.springbootdocker.pojo.common.ResponseBaseInfo;
import lombok.Data;

/**
 * @author dark
 **/
@Data
public class LxmUserLoginResponse extends ResponseBaseInfo {
    private String token;
}
