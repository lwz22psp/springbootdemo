package com.hapiniu.demo.springbootdocker.service.impl;

import com.hapiniu.demo.springbootdocker.model.LxmUserModel;
import com.hapiniu.demo.springbootdocker.service.AuthService;
import com.hapiniu.demo.springbootdocker.service.MyUserDetailsService;
import com.hapiniu.demo.springbootdocker.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * @author dark
 **/
@Service
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private MyUserDetailsService myUserDetailsService;
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setMyUserDetailsService(MyUserDetailsService myUserDetailsService) {
        this.myUserDetailsService = myUserDetailsService;
    }

    @Autowired
    public void setJwtTokenUtil(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    /**
     * login
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public String login(String username, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);
        return jwtTokenUtil.generateToken(userDetails);
    }

    @Override
    public LxmUserModel getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (LxmUserModel)authentication.getPrincipal();
    }

}
