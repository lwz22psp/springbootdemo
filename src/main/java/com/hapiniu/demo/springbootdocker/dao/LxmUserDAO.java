package com.hapiniu.demo.springbootdocker.dao;

import com.hapiniu.demo.springbootdocker.entity.LxmUser;
import com.hapiniu.demo.springbootdocker.entity.LxmUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LxmUserDAO {
    long countByExample(LxmUserExample example);

    int deleteByExample(LxmUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LxmUser record);

    int insertSelective(LxmUser record);

    List<LxmUser> selectByExample(LxmUserExample example);

    LxmUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LxmUser record, @Param("example") LxmUserExample example);

    int updateByExample(@Param("record") LxmUser record, @Param("example") LxmUserExample example);

    int updateByPrimaryKeySelective(LxmUser record);

    int updateByPrimaryKey(LxmUser record);
}