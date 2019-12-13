package com.hapiniu.demo.springbootdocker.bo;

import com.alibaba.fastjson.JSON;
import com.hapiniu.demo.springbootdocker.dao.LxmUserDAO;
import com.hapiniu.demo.springbootdocker.dao.LxmUserInfoDAO;
import com.hapiniu.demo.springbootdocker.entity.LxmUser;
import com.hapiniu.demo.springbootdocker.entity.LxmUserExample;
import com.hapiniu.demo.springbootdocker.entity.LxmUserInfo;
import com.hapiniu.demo.springbootdocker.entity.LxmUserInfoExample;
import com.hapiniu.demo.springbootdocker.model.LxmUserModel;
import com.hapiniu.demo.springbootdocker.model.common.PageModel;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

/**
 * @author dark
 **/
public class LxmUserBoTest {

    @Test
    public void getLxmUserModelByIdTest(){
        LxmUserDAO lxmUserDAO = mock(LxmUserDAO.class);
        when(lxmUserDAO.selectByPrimaryKey(anyInt())).thenReturn(generateLxmUser(1));
        LxmUserInfoDAO lxmUserInfoDAO = mock(LxmUserInfoDAO.class);
        when(lxmUserInfoDAO.selectByExample(any(LxmUserInfoExample.class))).thenReturn(Collections.singletonList(generateLxmUserInfo(1)));

        LxmUserBo bo = new LxmUserBo();
        bo.setLxmUserDAO(lxmUserDAO);
        bo.setLxmUserInfoDAO(lxmUserInfoDAO);

        LxmUserModel model = bo.getLxmUserModelById(1);
        Assert.assertEquals(model.getUserId(),new Integer(1));
        Assert.assertEquals(model.getNickName(),"mock_nick_name_1");
        Assert.assertEquals(model.getEmail(),"mock_email_1@test.com");
        Assert.assertEquals(model.getUserPwd(),"e10adc3949ba59abbe56e057f20f883e");

        when(lxmUserDAO.selectByPrimaryKey(anyInt())).thenReturn(null);
        model = bo.getLxmUserModelById(1);
        Assert.assertNull(model);
    }

    @Test
    public void verifyUsrNameTest(){
        LxmUserDAO lxmUserDAO = mock(LxmUserDAO.class);
        LxmUserInfoDAO lxmUserInfoDAO = mock(LxmUserInfoDAO.class);
        when(lxmUserDAO.selectByExample(any(LxmUserExample.class))).thenReturn(Collections.singletonList(generateLxmUser(1)));
        when(lxmUserInfoDAO.selectByExample(any(LxmUserInfoExample.class))).thenReturn(Collections.singletonList(generateLxmUserInfo(1)));

        LxmUserBo bo = new LxmUserBo();
        bo.setLxmUserDAO(lxmUserDAO);
        bo.setLxmUserInfoDAO(lxmUserInfoDAO);

        Assert.assertTrue(bo.verifyUsrName("mock_user_name_1"));
        when(lxmUserDAO.selectByExample(any(LxmUserExample.class))).thenReturn(Collections.emptyList());
        Assert.assertTrue(bo.verifyUsrName("mock_nick_name_1"));
        when(lxmUserInfoDAO.selectByExample(any(LxmUserInfoExample.class))).thenReturn(Collections.emptyList());
        Assert.assertFalse(bo.verifyUsrName("mock_nick_name_1"));
    }

    @Test
    public void loginTest(){
        LxmUserDAO lxmUserDAO = mock(LxmUserDAO.class);
        LxmUserInfoDAO lxmUserInfoDAO = mock(LxmUserInfoDAO.class);
        when(lxmUserDAO.selectByExample(any(LxmUserExample.class))).thenReturn(Collections.singletonList(generateLxmUser(1)));
        when(lxmUserDAO.selectByPrimaryKey(anyInt())).thenReturn(generateLxmUser(1));
        when(lxmUserInfoDAO.selectByExample(any(LxmUserInfoExample.class))).thenReturn(Collections.singletonList(generateLxmUserInfo(1)));

        LxmUserBo bo = new LxmUserBo();
        bo.setLxmUserDAO(lxmUserDAO);
        bo.setLxmUserInfoDAO(lxmUserInfoDAO);
        //login success
        Assert.assertEquals(1, bo.login("mock_user_name_1", "123456"));
        //login pwd error
        Assert.assertEquals(0, bo.login("mock_user_name_1", "123455"));

        when(lxmUserDAO.selectByExample(any(LxmUserExample.class))).thenReturn(Collections.emptyList());
        when(lxmUserInfoDAO.selectByExample(any(LxmUserInfoExample.class))).thenReturn(Collections.emptyList());
        //login no user
        Assert.assertEquals(0, bo.login("mock_user_name_1", "123456"));
    }

    @Test
    public void addLxmUserTest(){
        LxmUserDAO lxmUserDAO = mock(LxmUserDAO.class);
        LxmUserInfoDAO lxmUserInfoDAO = mock(LxmUserInfoDAO.class);

        LxmUserBo bo = new LxmUserBo();
        bo.setLxmUserDAO(lxmUserDAO);
        bo.setLxmUserInfoDAO(lxmUserInfoDAO);
        when(lxmUserInfoDAO.selectByExample(any(LxmUserInfoExample.class))).thenReturn(Collections.singletonList(generateLxmUserInfo(1)));

        bo.addLxmUser(generateLxmUserModel());
        verify(lxmUserDAO).insert(any());
        verify(lxmUserInfoDAO).updateByPrimaryKeySelective(any());

        when(lxmUserInfoDAO.selectByExample(any(LxmUserInfoExample.class))).thenReturn(Collections.emptyList());
        bo.addLxmUser(generateLxmUserModel());
        verify(lxmUserInfoDAO).insert(any());

    }

    @Test
    public void deletedeleteLxmUserModelTest(){
        LxmUserDAO lxmUserDAO = mock(LxmUserDAO.class);
        LxmUserInfoDAO lxmUserInfoDAO = mock(LxmUserInfoDAO.class);

        LxmUserBo bo = new LxmUserBo();
        bo.setLxmUserDAO(lxmUserDAO);
        bo.setLxmUserInfoDAO(lxmUserInfoDAO);

        bo.deleteLxmUserModel(1);
        verify(lxmUserDAO).updateByPrimaryKeySelective(any());
        verify(lxmUserInfoDAO).updateByPrimaryKeySelective(any());
    }

    @Test
    public void lstLxmUserModelTest(){
        LxmUserDAO lxmUserDAO = mock(LxmUserDAO.class);
        LxmUserInfoDAO lxmUserInfoDAO = mock(LxmUserInfoDAO.class);

        LxmUserBo bo = new LxmUserBo();
        bo.setLxmUserDAO(lxmUserDAO);
        bo.setLxmUserInfoDAO(lxmUserInfoDAO);
        when(lxmUserDAO.selectByExample(any(LxmUserExample.class))).thenReturn(generateLxmUserList(10));
        when(lxmUserInfoDAO.selectByExample(any(LxmUserInfoExample.class))).thenReturn(generateLxmUserInfoList(10));

        List<LxmUserModel> result = bo.lstLxmUserModel("",new PageModel());
        Assert.assertEquals(10, result.size());
        when(lxmUserDAO.selectByExample(any(LxmUserExample.class))).thenReturn(new ArrayList<>());
        when(lxmUserInfoDAO.selectByExample(any(LxmUserInfoExample.class))).thenReturn(new ArrayList<>());

        List<LxmUserModel> result2 = bo.lstLxmUserModel("",new PageModel());
        Assert.assertEquals(0, result.size());
    }

    private List<LxmUser> generateLxmUserList(int count){
        List<LxmUser> result = new ArrayList<>();
        for(int i=1;i<=count;i++){
            result.add(generateLxmUser(i));
        }
        return result;
    }

    private List<LxmUserInfo> generateLxmUserInfoList(int count){
        List<LxmUserInfo> result = new ArrayList<>();
        for(int i=1;i<=count;i++){
            result.add(generateLxmUserInfo(i));
        }
        return result;
    }

    private LxmUser generateLxmUser(int userId) {
        String jsonString = "{\"createTime\":1564538701567,\"creater\":\"\",\"id\":" + userId + ",\"status\":true,\"updateTime\":1564538701568,\"updater\":\"\",\"wechatUnionID\":\"\",\"userName\":\"mock_user_name_" + userId + "\",\"userPassword\":\"e10adc3949ba59abbe56e057f20f883e\"}\n";
        return JSON.parseObject(jsonString, LxmUser.class);
    }

    private LxmUserInfo generateLxmUserInfo(int userId) {
        LxmUserInfo result = new LxmUserInfo();
        result.setCreater("");
        result.setCreateTime(new Date());
        result.setId(userId);
        result.setNickName("mock_nick_name_" + userId);
        result.setStatus(true);
        result.setUpdater("");
        result.setUpdateTime(new Date());
        result.setUserEmail("mock_email_" + userId + "@test.com");
        result.setUserPhone("156" + (int) (Math.random() * 90000000 + 10000000));
        result.setUserId(userId);
        return result;
    }

    private LxmUserModel generateLxmUserModel(){
        LxmUserModel result = new LxmUserModel();
        result.setUserId(0);
        result.setUserName("mock_user_name_1");
        result.setUserPwd("e10adc3949ba59abbe56e057f20f883e");
        result.setPhone("156" + (int) (Math.random() * 90000000 + 10000000));
        result.setEmail("mock_email_1@test.com");
        result.setNickName("mock_nick_name_1");
        result.setWechatUnionID("mock_wechatUnionID_1");
        return result;
    }
}
