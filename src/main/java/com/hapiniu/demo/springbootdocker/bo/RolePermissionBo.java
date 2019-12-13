package com.hapiniu.demo.springbootdocker.bo;

import com.hapiniu.demo.springbootdocker.dao.LxmSysPermissionDAO;
import com.hapiniu.demo.springbootdocker.dao.LxmSysRoleDAO;
import com.hapiniu.demo.springbootdocker.dao.LxmSysRolePermissionDAO;
import com.hapiniu.demo.springbootdocker.dao.LxmSysUserRoleDAO;
import com.hapiniu.demo.springbootdocker.entity.*;
import com.hapiniu.demo.springbootdocker.model.RoleModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dark
 **/
@Component
public class RolePermissionBo {

    //region Autowired

    private LxmSysPermissionDAO lxmSysPermissionDAO;
    private LxmSysRoleDAO lxmSysRoleDAO;
    private LxmSysRolePermissionDAO lxmSysRolePermissionDAO;
    private LxmSysUserRoleDAO lxmSysUserRoleDAO;

    @Autowired
    public void setLxmSysPermissionDAO(LxmSysPermissionDAO lxmSysPermissionDAO) {
        this.lxmSysPermissionDAO = lxmSysPermissionDAO;
    }

    @Autowired
    public void setLxmSysRoleDAO(LxmSysRoleDAO lxmSysRoleDAO) {
        this.lxmSysRoleDAO = lxmSysRoleDAO;
    }

    @Autowired
    public void setLxmSysRolePermissionDAO(LxmSysRolePermissionDAO lxmSysRolePermissionDAO) {
        this.lxmSysRolePermissionDAO = lxmSysRolePermissionDAO;
    }

    @Autowired
    public void setLxmSysUserRoleDAO(LxmSysUserRoleDAO lxmSysUserRoleDAO) {
        this.lxmSysUserRoleDAO = lxmSysUserRoleDAO;
    }
    //endregion

    private void insertRole(LxmSysRole entity) {
        lxmSysRoleDAO.insert(entity);
    }

    private void updateRole(LxmSysRole entity) {
        lxmSysRoleDAO.updateByPrimaryKeySelective(entity);
    }

    private void deleteRole(Integer roleId) {
        lxmSysRoleDAO.deleteByPrimaryKey(roleId);
        deleteLxmSysRolePermissionByRoleId(roleId);
        deleteLxmSysUserRoleByRoleId(roleId);
    }

    private void insertPermission(LxmSysPermission entity) {
        lxmSysPermissionDAO.insert(entity);
    }

    private void updatePermission(LxmSysPermission entity){
        lxmSysPermissionDAO.updateByPrimaryKeySelective(entity);
    }

    private void deletePermission(Integer permissionId){
        lxmSysPermissionDAO.deleteByPrimaryKey(permissionId);
        deleteLxmSysRolePermissionByPermissionId(permissionId);
    }

    private void deleteLxmSysRolePermissionByRoleId(Integer roleId) {
        LxmSysRolePermissionExample lxmSysRolePermissionExample = new LxmSysRolePermissionExample();
        lxmSysRolePermissionExample.createCriteria().andRoleIdEqualTo(roleId);
        lxmSysRolePermissionDAO.deleteByExample(lxmSysRolePermissionExample);
    }

    private void deleteLxmSysRolePermissionByPermissionId(Integer permissionId) {
        LxmSysRolePermissionExample lxmSysRolePermissionExample = new LxmSysRolePermissionExample();
        lxmSysRolePermissionExample.createCriteria().andPermissionIdEqualTo(permissionId);
        lxmSysRolePermissionDAO.deleteByExample(lxmSysRolePermissionExample);
    }

    private void deleteLxmSysUserRoleByRoleId(Integer roleId){
        LxmSysUserRoleExample lxmSysUserRoleExample = new LxmSysUserRoleExample();
        lxmSysUserRoleExample.createCriteria().andRoleIdEqualTo(roleId);
        lxmSysUserRoleDAO.deleteByExample(lxmSysUserRoleExample);
    }

    public void deleteLxmSysUserRoleByUserId(Integer userId){
        LxmSysUserRoleExample lxmSysUserRoleExample = new LxmSysUserRoleExample();
        lxmSysUserRoleExample.createCriteria().andUserIdEqualTo(userId);
        lxmSysUserRoleDAO.deleteByExample(lxmSysUserRoleExample);
    }

    public List<RoleModel> lstRolesByUserId(Integer userId){
        LxmSysUserRoleExample lxmSysUserRoleExample = new LxmSysUserRoleExample();
        lxmSysUserRoleExample.createCriteria().andUserIdEqualTo(userId);
        List<Integer> roleIdList = lxmSysUserRoleDAO.selectByExample(lxmSysUserRoleExample).stream()
                .map(LxmSysUserRole::getRoleId).distinct().collect(Collectors.toList());
        if(roleIdList.isEmpty()) return new ArrayList<>();
        LxmSysRoleExample lxmSysRoleExample = new LxmSysRoleExample();
        lxmSysRoleExample.createCriteria().andIdIn(roleIdList);
        return lxmSysRoleDAO.selectByExample(lxmSysRoleExample).
                stream().map(this::convertToModel).collect(Collectors.toList());
    }

    private RoleModel convertToModel(LxmSysRole entity){
        RoleModel model = new RoleModel();
        BeanUtils.copyProperties(entity,model);
        return model;
    }
}
