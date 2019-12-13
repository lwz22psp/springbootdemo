package com.hapiniu.demo.springbootdocker.service;

import com.hapiniu.demo.springbootdocker.bo.LxmUserBo;
import com.hapiniu.demo.springbootdocker.bo.RolePermissionBo;
import com.hapiniu.demo.springbootdocker.model.LxmUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author dark
 **/
@Service
public class MyUserDetailsService implements UserDetailsService {

    //region Autowired

    private LxmUserBo lxmUserBo;
    private RolePermissionBo rolePermissionBo;

    @Autowired
    public void setLxmUserBo(LxmUserBo lxmUserBo) {
        this.lxmUserBo = lxmUserBo;
    }

    @Autowired
    public void setRolePermissionBo(RolePermissionBo rolePermissionBo) {
        this.rolePermissionBo = rolePermissionBo;
    }
    //endregion

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        LxmUserModel userModel = lxmUserBo.getLxmUserModelByString(s);
        if(userModel==null){
            throw new UsernameNotFoundException("用户不存在");
        }else{
            userModel.setRoles(rolePermissionBo.lstRolesByUserId(userModel.getUserId()));
        }

        return userModel;
    }
}
