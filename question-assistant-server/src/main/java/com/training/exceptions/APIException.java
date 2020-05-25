package com.training.exceptions;

import org.springframework.http.HttpStatus;

/**
 * API异常
 */
public class APIException extends RuntimeException {
    public final static int SUCC=1000; //成功
    public final static int ERR_1001=1001; //业务异常,通常用于弹窗提示直接输出错误,如存在关联无法删除之类的业务异常
    public final static int ERR_1002=1002; //登录令牌错误,需要刷新令牌后重新尝试登录
    public final static int ERR_1003=1003; //参数错误,用于参数校验失败后定位到错误字段,返回json数组如:[{\"email\":\"邮箱格式错误\"},...]
    public final static int ERR_1004=1004; //刷新令牌失败,需要重新登录
    public final static int ERR_1005=1005; //没有访问权限
    public final static int ERR_1006=1006; //服务器内部异常,必须处理,出现后请反馈给后端,带上错误编号即可,例: [错误编号]:null point Exception

    private int code;

    public APIException(final int code, final String message) {
        super(message);
        this.code=code;
    }

    public APIException(final int code, final String message, Throwable cause) {
        super(message, cause);
        this.code=code;
    }

    public APIException(final int code, final Throwable cause) {
        super(cause);
        this.code=code;
    }

    public APIException(final int code, final String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code=code;
    }

    public APIException(final HttpStatus httpStatus, final String message){
        this(httpStatus.value(), message);
    }

    public APIException(final HttpStatus httpStatus, final String message, Throwable cause) {
        this(httpStatus.value(), message, cause);
    }
}
