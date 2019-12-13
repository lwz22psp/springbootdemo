package com.hapiniu.demo.springbootdocker.service;

import com.hapiniu.demo.springbootdocker.model.LxmUserModel;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author dark
 */
public interface AuthService {
    /**
     * login
     * @param username
     * @param password
     * @return
     */
    String login(String username, String password);

    LxmUserModel getUser();
}
