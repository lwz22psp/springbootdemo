package com.hapiniu.demo.springbootdocker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.List;

/**
 * @author dark
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LxmUserModel implements UserDetails, Serializable {
    private Integer userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * password
     */
    private String userPwd;
    /**
     * phone
     */
    private String phone;
    /**
     * email
     */
    private String email;
    /**
     * nickName
     */
    private String nickName;

    private String wechatUnionID;

    private List<RoleModel> roles;

    @Override
    public List<RoleModel> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return userPwd;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
