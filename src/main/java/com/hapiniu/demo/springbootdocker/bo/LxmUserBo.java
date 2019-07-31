package com.hapiniu.demo.springbootdocker.bo;

import com.hapiniu.demo.springbootdocker.dao.LxmUserDAO;
import com.hapiniu.demo.springbootdocker.dao.LxmUserInfoDAO;
import com.hapiniu.demo.springbootdocker.entity.LxmUser;
import com.hapiniu.demo.springbootdocker.entity.LxmUserExample;
import com.hapiniu.demo.springbootdocker.entity.LxmUserInfo;
import com.hapiniu.demo.springbootdocker.entity.LxmUserInfoExample;
import com.hapiniu.demo.springbootdocker.model.LxmUserModel;
import com.hapiniu.demo.springbootdocker.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LxmUserBo {

    //region Autowired
    private LxmUserDAO lxmUserDAO;
    private LxmUserInfoDAO lxmUserInfoDAO;

    @Autowired
    public void setLxmUserInfoDAO(LxmUserInfoDAO lxmUserInfoDAO) {
        this.lxmUserInfoDAO = lxmUserInfoDAO;
    }

    @Autowired
    public void setLxmUserDAO(LxmUserDAO lxmUserDAO) {
        this.lxmUserDAO = lxmUserDAO;
    }
    //endregion

    public void addLxmUser(LxmUserModel model) {
        LxmUser entity = convertToLxmUserEntity(model);
        insertLxmUser(entity);
        model.setUserId(entity.getId());
        saveLxmUserInfo(convertToLxmUserInfoEntity(model), "AUTO");
    }

    public boolean verifyUsrName(String userName) {
        return findUser(userName)>0;
    }

    public int login(String prarm,String userPwd){
        int uid = findUser(prarm);
        if(uid==0){
            return 0;
        }
        LxmUserModel userModel = getLxmUserModelById(uid);
        return  userModel.getUserPwd().equals(MD5.eccrypt(userPwd))?uid:0;
    }

    private int findUser(String param){
        LxmUserExample query = new LxmUserExample();
        query.createCriteria().andStatusEqualTo(true).andUserNameEqualTo(param);
        List<LxmUser> result = queryLxmUser(query);
        if(queryLxmUser(query).isEmpty()){
            LxmUserInfoExample queryEntity = new LxmUserInfoExample();
            queryEntity.createCriteria();
            queryEntity.or().andNickNameEqualTo(param);
            queryEntity.or().andUserPhoneEqualTo(param);
            queryEntity.or().andUserEmailEqualTo(param);
            List<LxmUserInfo> lxmUserInfoList = queryLxmUserInfo(queryEntity);
            return lxmUserInfoList.isEmpty()?0:lxmUserInfoList.get(0).getUserId();
        }else{
            return result.get(0).getId();
        }
    }

    public LxmUserModel getLxmUserModelById(Integer userId) {
        LxmUser user = queryLxmUserById(userId);
        if (user == null) {
            return null;
        }
        LxmUserInfo userInfo = queryByUserId(userId);
        return convertToModel(user, userInfo);
    }

    private LxmUserModel convertToModel(LxmUser userEntity, LxmUserInfo userInfoEntity) {
        LxmUserModel model = new LxmUserModel();
        model.setUserId(userEntity.getId());
        model.setUserName(userEntity.getUserName());
        model.setUserPwd(userEntity.getUserPassword());
        model.setWechatUnionID(userEntity.getWechatUnionID());
        if (userInfoEntity != null) {
            model.setEmail(userInfoEntity.getUserEmail());
            model.setPhone(userInfoEntity.getUserPhone());
            model.setNickName(userInfoEntity.getNickName());
        }
        return model;
    }

    //region LxmUser function
    private LxmUser convertToLxmUserEntity(LxmUserModel dataInfo) {
        LxmUser model = new LxmUser();
        model.setUserName(dataInfo.getUserName());
        model.setId(dataInfo.getUserId());
        model.setUserPassword(dataInfo.getUserPwd());
        return model;
    }

    private void insertLxmUser(LxmUser entity) {
        entity.setCreater("AUTO");
        entity.setUpdater("AUTO");
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        entity.setStatus(true);
        lxmUserDAO.insert(entity);
    }

    private void updateLxmUser(LxmUser entity, String updater) {
        entity.setUpdater(updater);
        entity.setUpdateTime(new Date());
        entity.setStatus(true);
        lxmUserDAO.updateByPrimaryKeySelective(entity);
    }

    private LxmUser queryLxmUserById(Integer id) {
        return lxmUserDAO.selectByPrimaryKey(id);
    }

    private List<LxmUser> queryLxmUser(LxmUserExample example) {
        return lxmUserDAO.selectByExample(example);
    }
    //endregion


    //region LxmUserInfo function
    private void saveLxmUserInfo(LxmUserInfo entity, String updater) {
        LxmUserInfo searchResult = queryByUserId(entity.getUserId());
        if (searchResult == null) {
            insertLxmUserInfo(entity);
        } else {
            //update
            entity.setId(searchResult.getId());
            updateLxmUserInfo(entity, updater);
        }
    }

    private LxmUserInfo queryByUserId(Integer userId) {
        LxmUserInfoExample queryEntity = new LxmUserInfoExample();
        queryEntity.createCriteria().andStatusEqualTo(true).andUserIdEqualTo(userId);
        List<LxmUserInfo> lxmUserInfoList = lxmUserInfoDAO.selectByExample(queryEntity);
        return lxmUserInfoList.isEmpty() ? null : lxmUserInfoList.get(0);
    }

    private List<LxmUserInfo> queryLxmUserInfo(LxmUserInfoExample queryEntity) {
        return lxmUserInfoDAO.selectByExample(queryEntity);
    }

    private void insertLxmUserInfo(LxmUserInfo entity) {
        entity.setCreater("AUTO");
        entity.setUpdater("AUTO");
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        entity.setStatus(true);
        lxmUserInfoDAO.insert(entity);
    }

    private void updateLxmUserInfo(LxmUserInfo entity, String updater) {
        entity.setUpdater(updater);
        entity.setUpdateTime(new Date());
        lxmUserInfoDAO.updateByPrimaryKeySelective(entity);
    }

    private LxmUserInfo convertToLxmUserInfoEntity(LxmUserModel dataInfo) {
        LxmUserInfo model = new LxmUserInfo();
        model.setUserId(dataInfo.getUserId());
        model.setUserEmail(dataInfo.getEmail());
        model.setUserPhone(dataInfo.getPhone());
        model.setNickName(dataInfo.getNickName());
        return model;
    }
    //endregion
}
