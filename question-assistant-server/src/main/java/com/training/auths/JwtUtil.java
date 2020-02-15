package com.training.auths;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.common.objects.Result;
import com.common.utils.DateUtil;
import com.training.exceptions.APIException;

import java.util.Date;

public class JwtUtil {
    /**
     * 加签
     * @param authInfo
     * @param secret
     * @return
     */
    public static String sign(JwtAuthInfo authInfo, final String secret, final Date expiresDate){
        var expireDate= DateUtil.incHour(new Date(), 1);
        var result= JWT.create();
        for (String k: authInfo.keys()){
            result.withClaim(k, authInfo.get(k, ""));
        }
        var ret=result.withExpiresAt(expireDate)//一小时后过期
              .sign(Algorithm.HMAC256(secret));
        return ret;
    }

    /**
     * 加签
     * @param authInfo
     * @param secret
     * @return
     */
    public static String sign(JwtAuthInfo authInfo, final String secret){
        var expireDate= DateUtil.incDay(new Date(), 1);
        var ret=sign(authInfo, secret, expireDate);
        return ret;
    }

    /**
     * 验签
     * @param token
     * @param authInfo
     * @param secret
     * @return
     */
    public static Result<Boolean> verify(final String token, JwtAuthInfo authInfo, final String secret){
        var verifier=JWT.require(Algorithm.HMAC256(secret));
        for(String k : authInfo.keys()) {
            verifier.withClaim(k, authInfo.get(k, ""));
        }
        var jwtVerifier=verifier.build();
        //检查token
        try{
            DecodedJWT jwt=jwtVerifier.verify(token);
            System.out.println(jwt);//看下返回的jwt里面包含什么内容
            return Result.succes(true);
        }catch (TokenExpiredException e1){
            return Result.fail(APIException.ERR_1002, "令牌过期");
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(APIException.ERR_1002, "登录验证失败");
        }
    }


    /**
     * 从token中提取数据
     * @param token
     * @param fieldName
     * @return
     */
    public static String getAttrFromToken(final String token, final String fieldName){
        try{
            DecodedJWT jwt=JWT.decode(token);
            return jwt.getClaim(fieldName).asString();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
