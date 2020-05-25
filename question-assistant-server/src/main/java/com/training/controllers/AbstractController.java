package com.training.controllers;

import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.math.BigInteger;

public class AbstractController {
    /**
     * 检查参数值是否有效
     * @param param
     * @param errMessage
     */
    protected void paramIsNull(final Object param, final String errMessage){
        Assert.isTrue(!this.paramIsNull(param), errMessage);
    }

    protected boolean paramIsNull(final Object param){
        BigDecimal val=null;
        if (param ==null){
            return true;
        }else if (param instanceof Integer){
            val=BigDecimal.valueOf((Integer)param);
            return val.compareTo(BigDecimal.ZERO)==0;
        }else if (param instanceof Long){
            val=BigDecimal.valueOf((Long)param);
            return val.compareTo(BigDecimal.ZERO)==0;
        }else if (param instanceof BigInteger){
            val=BigDecimal.valueOf((Long)param);
            return val.compareTo(BigDecimal.ZERO)==0;
        }else if (param instanceof Double){
            val=BigDecimal.valueOf((Double)param);
            return val.compareTo(BigDecimal.ZERO)==0;
        }else if (param instanceof Float){
            val=BigDecimal.valueOf((Float)param);
            return val.compareTo(BigDecimal.ZERO)==0;
        }else if (param instanceof BigDecimal){
            val=(BigDecimal)param;
            return val.compareTo(BigDecimal.ZERO)==0;
        }else if (param instanceof Byte) {
            val = new BigDecimal((Byte) param);
            return val.compareTo(BigDecimal.ZERO)==0;
        }else if (param instanceof String){
            String s=(String)param;
            return "".equals(s);
        }
        return false;
    }
}
