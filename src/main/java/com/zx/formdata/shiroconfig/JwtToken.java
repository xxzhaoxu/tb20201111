package com.zx.formdata.shiroconfig;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author create by zhaoxu
 * @create 2020/11/15
 */
public class JwtToken implements AuthenticationToken {
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
