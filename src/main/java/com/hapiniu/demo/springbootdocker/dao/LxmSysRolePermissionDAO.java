package com.hapiniu.demo.springbootdocker.dao;

import com.hapiniu.demo.springbootdocker.entity.LxmSysRolePermission;
import com.hapiniu.demo.springbootdocker.entity.LxmSysRolePermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LxmSysRolePermissionDAO {
    long countByExample(LxmSysRolePermissionExample example);

    int deleteByExample(LxmSysRolePermissionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LxmSysRolePermission record);

    int insertSelective(LxmSysRolePermission record);

    List<LxmSysRolePermission> selectByExample(LxmSysRolePermissionExample example);

    LxmSysRolePermission selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LxmSysRolePermission record, @Param("example") LxmSysRolePermissionExample example);

    int updateByExample(@Param("record") LxmSysRolePermission record, @Param("example") LxmSysRolePermissionExample example);

    int updateByPrimaryKeySelective(LxmSysRolePermission record);

    int updateByPrimaryKey(LxmSysRolePermission record);
}