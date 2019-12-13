package com.hapiniu.demo.springbootdocker.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author dark
 **/
@Data
public class RoleModel implements GrantedAuthority {
    private Integer id;

    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
