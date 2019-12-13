package com.hapiniu.demo.springbootdocker.pojo;

import com.hapiniu.demo.springbootdocker.model.LxmUserModel;
import com.hapiniu.demo.springbootdocker.model.common.PageModel;
import com.hapiniu.demo.springbootdocker.pojo.common.ResponseBaseInfo;
import lombok.Data;

import java.util.List;

/**
 * @author dark
 **/
@Data
public class SearchLxmUserResponse extends ResponseBaseInfo {
   private List<LxmUserModel> data;
   private PageModel pageInfo;
}
