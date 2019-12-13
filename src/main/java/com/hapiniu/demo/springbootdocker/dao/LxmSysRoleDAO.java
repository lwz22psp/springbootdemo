package com.hapiniu.demo.springbootdocker.dao;

import com.hapiniu.demo.springbootdocker.entity.LxmSysRole;
import com.hapiniu.demo.springbootdocker.entity.LxmSysRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LxmSysRoleDAO {
    long countByExample(LxmSysRoleExample example);

    int deleteByExample(LxmSysRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LxmSysRole record);

    int insertSelective(LxmSysRole record);

    List<LxmSysRole> selectByExample(LxmSysRoleExample example);

    LxmSysRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LxmSysRole record, @Param("example") LxmSysRoleExample example);

    int updateByExample(@Param("record") LxmSysRole record, @Param("example") LxmSysRoleExample example);

    int updateByPrimaryKeySelective(LxmSysRole record);

    int updateByPrimaryKey(LxmSysRole record);
}