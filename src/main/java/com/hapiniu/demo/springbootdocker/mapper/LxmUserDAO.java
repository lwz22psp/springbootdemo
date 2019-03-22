package com.hapiniu.demo.springbootdocker.mapper;

import com.hapiniu.demo.springbootdocker.entity.LxmUser;
import com.hapiniu.demo.springbootdocker.entity.LxmUserExample;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.apache.ibatis.annotations.Param;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Repository;

@Repository
public interface LxmUserDAO {
    long countByExample(LxmUserExample example);

    int deleteByExample(LxmUserExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(LxmUser record);

    int insertSelective(LxmUser record);

    List<LxmUser> selectByExample(LxmUserExample example);

    LxmUser selectByPrimaryKey(Integer userId);

    int updateByExampleSelective(@Param("record") LxmUser record, @Param("example") LxmUserExample example);

    int updateByExample(@Param("record") LxmUser record, @Param("example") LxmUserExample example);

    int updateByPrimaryKeySelective(LxmUser record);

    int updateByPrimaryKey(LxmUser record);
}