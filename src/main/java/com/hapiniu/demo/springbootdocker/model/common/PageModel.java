package com.hapiniu.demo.springbootdocker.model.common;

import lombok.Data;

/**
 * @author dark
 **/
@Data
public class PageModel {
    private int pageIndex;
    private int pageCount;
    private int totalCount;
}
