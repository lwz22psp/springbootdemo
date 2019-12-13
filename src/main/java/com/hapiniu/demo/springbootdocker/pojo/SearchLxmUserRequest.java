package com.hapiniu.demo.springbootdocker.pojo;

import com.hapiniu.demo.springbootdocker.model.common.PageModel;
import com.hapiniu.demo.springbootdocker.pojo.common.RequestBaseInfo;
import lombok.Data;

@Data
public class SearchLxmUserRequest extends RequestBaseInfo {
   private String searchParam;
    private PageModel pageInfo;
}
