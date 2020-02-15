package com.training.auths;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * jwt的token
 */
public class JwtToken implements AuthenticationToken {
    private String token;

    public JwtToken(final String token){
        this.token=token;
    }

    @Override
    public Object getPrincipal() {
        return this.token;
    }

    @Override
    public Object getCredentials() {
        return this.token;
    }
}
