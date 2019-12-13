package com.hapiniu.demo.springbootdocker.dao;

import com.hapiniu.demo.springbootdocker.entity.LxmSysPermission;
import com.hapiniu.demo.springbootdocker.entity.LxmSysPermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LxmSysPermissionDAO {
    long countByExample(LxmSysPermissionExample example);

    int deleteByExample(LxmSysPermissionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LxmSysPermission record);

    int insertSelective(LxmSysPermission record);

    List<LxmSysPermission> selectByExample(LxmSysPermissionExample example);

    LxmSysPermission selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LxmSysPermission record, @Param("example") LxmSysPermissionExample example);

    int updateByExample(@Param("record") LxmSysPermission record, @Param("example") LxmSysPermissionExample example);

    int updateByPrimaryKeySelective(LxmSysPermission record);

    int updateByPrimaryKey(LxmSysPermission record);
}