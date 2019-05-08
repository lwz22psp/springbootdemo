package com.hapiniu.demo.springbootdocker.pojo;

import com.hapiniu.demo.springbootdocker.model.LxmUserModel;
import com.hapiniu.demo.springbootdocker.pojo.common.ResponseBaseInfo;
import lombok.Data;

/**
 * @author dark
 **/
@Data
public class GetLxmUserResponse extends ResponseBaseInfo {
   private LxmUserModel data;
}
