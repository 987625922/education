package com.project.gelingeducation.common.authentication;
 
import org.apache.shiro.authc.AuthenticationToken;

import java.io.Serializable;


public class JwtToken implements AuthenticationToken, Serializable {
	
	private static final long serialVersionUID = 1L;
	private String token;
 
    public JwtToken(String token) {
        this.token = token;
    }
 
    @Override
    public Object getPrincipal() {
        return token;
    }
 
    @Override
    public Object getCredentials() {
        return token;
    }
}
