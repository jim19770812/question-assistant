package com.common.objects;

import com.training.exceptions.APIException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashMap;

@Data
@ApiModel("API返回包装对象")
@ToString
@EqualsAndHashCode
public class Result<T> implements Serializable {
    public static int SUCCESS_CODE=1000;
    @ApiModelProperty(value = "错误编码")
    private int code;
    @ApiModelProperty(value = "错误信息")
    private String message;
    @ApiModelProperty(value = "返回数据")
    private T ret;

    public Result(final int code){
        this.message="";
        this.code=code;
        this.ret =null;
    }

    public Result(final int code, final String message){
        this.code=code;
        this.message=message;
        this.ret =null;
    }

    public Result(final int code, final String message, final T ret){
        this.code=code;
        this.message=message;
        this.ret = ret;
    }

    public static <T>boolean isSuccess(Result<T> ret){
        return ret !=null && ret.getCode()== APIException.SUCC;
    }

    public static <T>boolean isFail(Result<T> ret){
        return ret ==null || ret.getCode()!=APIException.SUCC;
    }

    public static <T> Result<T> succes(final T ret){
        var result=new Result(SUCCESS_CODE, "", ret);
        return result;
    }

    public static <T> Result<T> fail(final int code, final String message){
        var result=new Result(SUCCESS_CODE, message, new HashMap());
        return result;
    }
}
