package com.hapiniu.demo.springbootdocker.bo;

import com.hapiniu.demo.springbootdocker.dao.LxmUserDAO;
import com.hapiniu.demo.springbootdocker.entity.LxmUser;
import com.hapiniu.demo.springbootdocker.entity.LxmUserExample;
import com.hapiniu.demo.springbootdocker.model.LxmUserModel;
import com.hapiniu.demo.springbootdocker.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
        LxmUser entity=convertToEntity(model);
        insertLxmUser(entity);
    }

    public boolean verifyUsrName(String userName){
        LxmUserExample query = new LxmUserExample();
        query.createCriteria().andStatusEqualTo(true).andUserNameEqualTo(userName);
        return queryLxmUser(query).isEmpty();
    }

    private LxmUser convertToEntity(LxmUserModel dataInfo) {
        LxmUser model = new LxmUser();
        model.setUserName(dataInfo.getUserName());
        model.setId(dataInfo.getUserId());
        model.setUserPassword(dataInfo.getUserPwd());
        return model;
    }

    private LxmUserModel convertToModel(LxmUser dataInfo) {
        LxmUserModel model = new LxmUserModel();
        model.setUserId(dataInfo.getId());
        model.setUserName(dataInfo.getUserName());
        model.setUserPwd(dataInfo.getUserPassword());
        return model;
    }

    private void insertLxmUser(LxmUser entity){
        entity.setCreater("AUTO");
        entity.setUpdater("AUTO");
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        entity.setStatus(true);
        lxmUserDAO.insert(entity);
    }

    private void updateLxmUser(LxmUser entity,String updater){
        entity.setUpdater(updater);
        entity.setUpdateTime(new Date());
        entity.setStatus(true);
        lxmUserDAO.updateByPrimaryKeySelective(entity);
    }

    private LxmUser queryLxmUserById(Integer id){
        return lxmUserDAO.selectByPrimaryKey(id);
    }

    private List<LxmUser> queryLxmUser(LxmUserExample example){
        return lxmUserDAO.selectByExample(example);
    }
}
