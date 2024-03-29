package com.training.auths;

import com.common.objects.Result;
import com.common.utils.StringUtil;
import com.training.exceptions.APIException;
import com.training.mapper.MUserMapper;
import com.training.vos.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.annotation.Resource;

//https://blog.csdn.net/qq_23250633/article/details/81063762
@Slf4j
@Component
public class ShiroRealm extends AuthorizingRealm {
    @Resource
    private MUserMapper userMapper;

    /**
     * 必须重写此方法，否则shiro会报错
     * @param token
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 认证,登录
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) throws AuthenticationException {
        var token=principalCollection.toString();
        String usrId=JwtUtil.getAttrFromToken(token, "usr_id");
        if (StringUtil.isNull(usrId)){
            throw new AuthenticationException("令牌无效");
        }
        var usr=this.userMapper.selectById(usrId);
        var ret=(AuthorizationInfo)new SimpleAuthenticationInfo(token, token, "ShiroRealm");
        return ret;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        var token=(String)authenticationToken.getCredentials();
        String usrId=JwtUtil.getAttrFromToken(token, "usr_id");
        if (StringUtil.isNull(usrId)){
            throw new AuthenticationException("令牌无效");
        }
        var usr=this.userMapper.selectById(usrId);
        if (usr==null){
            throw new AuthenticationException("未能根据用户id找到用户");
        }
        //向请求作用域保存用户信息
        RequestContextHolder.getRequestAttributes().setAttribute("usrinfo", new UserInfo(usr.getUsr_id(), usr.getUsr_name(), usr.getUsr_email()), RequestAttributes.SCOPE_REQUEST);
        MDC.put("usr_id", usr !=null ? Integer.toString(usr.getUsr_id()):"");
        MDC.put("usr_name", usr !=null ? usr.getUsr_name():"");
        MDC.put("usr_email", usr !=null ? usr.getUsr_email():"");
        var ret=(AuthenticationInfo)new SimpleAuthenticationInfo(token, token, "ShiroRealm");
        return ret;
    }
}
