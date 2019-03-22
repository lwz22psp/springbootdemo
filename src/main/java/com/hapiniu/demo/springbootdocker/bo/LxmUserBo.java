package com.hapiniu.demo.springbootdocker.bo;

import com.hapiniu.demo.springbootdocker.entity.LxmUser;
import com.hapiniu.demo.springbootdocker.mapper.LxmUserDAO;
import com.hapiniu.demo.springbootdocker.model.LxmUserModel;
import com.hapiniu.demo.springbootdocker.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LxmUserBo {
    private LxmUserDAO lxmUserDAO;

    @Autowired
    public void setLxmUserDAO(LxmUserDAO lxmUserDAO) {
        this.lxmUserDAO = lxmUserDAO;
    }

    public void addLxmUser(String userName, String userPwd) {
        LxmUserModel model = new LxmUserModel();
        model.setUserName(userName);
        model.setUserPwd(MD5.eccrypt(userPwd));
        lxmUserDAO.insertSelective(convertToEntity(model));
    }

    private LxmUser convertToEntity(LxmUserModel dataInfo) {
        LxmUser model = new LxmUser();
        model.setUserId(dataInfo.getUserId());
        model.setUserName(dataInfo.getUserName());
        model.setUserPwd(dataInfo.getUserPwd());
        return model;
    }

    private LxmUserModel convertToModel(LxmUser dataInfo) {
        LxmUserModel model = new LxmUserModel();
        model.setUserId(dataInfo.getUserId());
        model.setUserName(dataInfo.getUserName());
        model.setUserPwd(dataInfo.getUserPwd());
        return model;
    }
}
