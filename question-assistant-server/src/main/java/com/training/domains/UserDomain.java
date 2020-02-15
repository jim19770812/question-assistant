package com.training.domains;

import com.training.auths.AuthenticationVerify;
import com.training.auths.JwtAuthInfo;
import com.training.auths.JwtUtil;
import com.training.beans.MUser;
import com.training.mapper.MUserMapper;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserDomain implements AuthenticationVerify {
    @Resource
    MUserMapper userMapper;

    @Override
    public JwtAuthInfo login(String authIndentify, String password) throws Exception {
        var usr=this.userMapper.findUserByEmailAndPassword(authIndentify, password);
        if (usr==null){
            throw new Exception("密码不正确");
        }
        var result=new JwtAuthInfo();
        result.put("usr_id", Integer.toString(usr.getUsr_id()));
        result.put("usr_name", usr.getUsr_name());
        result.put("usr_email", usr.getUsr_email());
        result.setObject(usr);
        var token= JwtUtil.sign(result, usr.getUsr_slat());
        result.setToken(token);
        return result;
    }
}
