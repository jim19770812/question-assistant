package com.training.domains;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.common.utils.UUIDUtils;
import com.training.auths.AuthenticationVerify;
import com.training.auths.JwtAuthInfo;
import com.training.auths.JwtUtil;
import com.training.beans.MUser;
import com.training.exceptions.APIException;
import com.training.mapper.MUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.data.convert.EntityWriter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
@Slf4j
public class UserDomain implements AuthenticationVerify {
    @Resource
    MUserMapper userMapper;

    public MUser register(String userName, String userEmail, String password) throws Exception{
        var wrapper=new QueryWrapper();
        wrapper.eq(MUser.COL_USR_EMAIL, userEmail);
        var cnt=this.userMapper.selectCount(wrapper);
        if (cnt==0){
            throw new APIException(APIException.ERR_1001, "用户已注册");
        }
        var usr=new MUser();
        usr.setUsr_email(userEmail);
        usr.setUsr_join_time(new Date());
        usr.setUsr_name(userName);
        usr.setUsr_slat(UUIDUtils.generate8Key());
        usr.setUsr_pwd(password);
        var ret=userMapper.insert(usr);
        log.debug(MessageFormatter.format("注册用户{}", userEmail).toString());
        return usr;
    }

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
