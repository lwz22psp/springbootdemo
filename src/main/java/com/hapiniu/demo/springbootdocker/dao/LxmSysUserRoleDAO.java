package com.hapiniu.demo.springbootdocker.dao;

import com.hapiniu.demo.springbootdocker.entity.LxmSysUserRole;
import com.hapiniu.demo.springbootdocker.entity.LxmSysUserRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LxmSysUserRoleDAO {
    long countByExample(LxmSysUserRoleExample example);

    int deleteByExample(LxmSysUserRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LxmSysUserRole record);

    int insertSelective(LxmSysUserRole record);

    List<LxmSysUserRole> selectByExample(LxmSysUserRoleExample example);

    LxmSysUserRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LxmSysUserRole record, @Param("example") LxmSysUserRoleExample example);

    int updateByExample(@Param("record") LxmSysUserRole record, @Param("example") LxmSysUserRoleExample example);

    int updateByPrimaryKeySelective(LxmSysUserRole record);

    int updateByPrimaryKey(LxmSysUserRole record);
}