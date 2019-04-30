package com.hapiniu.demo.springbootdocker.dao;

import com.hapiniu.demo.springbootdocker.entity.LxmUserInfo;
import com.hapiniu.demo.springbootdocker.entity.LxmUserInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LxmUserInfoDAO {
    long countByExample(LxmUserInfoExample example);

    int deleteByExample(LxmUserInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LxmUserInfo record);

    int insertSelective(LxmUserInfo record);

    List<LxmUserInfo> selectByExample(LxmUserInfoExample example);

    LxmUserInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LxmUserInfo record, @Param("example") LxmUserInfoExample example);

    int updateByExample(@Param("record") LxmUserInfo record, @Param("example") LxmUserInfoExample example);

    int updateByPrimaryKeySelective(LxmUserInfo record);

    int updateByPrimaryKey(LxmUserInfo record);
}