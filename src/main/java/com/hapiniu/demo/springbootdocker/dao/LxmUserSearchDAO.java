package com.hapiniu.demo.springbootdocker.dao;

import com.hapiniu.demo.springbootdocker.entity.LxmUserSearchResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dark
 */
@Repository
public interface LxmUserSearchDAO {
    /**
     * 查询用户
     * @param searchParam
     * @param index
     * @param size
     * @return
     */
    List<LxmUserSearchResult> searchUser(@Param("searchParam") String searchParam
            ,@Param("offset") Integer index
            ,@Param("limit") Integer size);

    /**
     * 计数
     * @param searchParam
     * @return
     */
    Long countUser(@Param("searchParam") String searchParam);
}
