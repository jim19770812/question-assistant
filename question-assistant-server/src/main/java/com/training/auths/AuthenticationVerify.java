package com.training.auths;

/**
 * 身份验证接口
 */
public interface AuthenticationVerify {
    /**
     * 身份验证
     * @param authEntity 验证主体,登录帐号,邮箱之类
     * @param password 验证密码
     * @return
     */
    JwtAuthInfo login(final String authEntity, final String password) throws Exception;
}
