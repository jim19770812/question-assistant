package com.training.exceptions;

import com.common.objects.Result;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.system.SystemProperties;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.annotation.Resource;
import javax.naming.NoPermissionException;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 全局异常处理
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(IllegalAccessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result illegalAccessExceptionException(IllegalAccessException e) {
        return Result.fail(1001, e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result illegalArgumentExceptionException(IllegalArgumentException e) {
        return Result.fail(1001, e.getMessage());
    }

    @ExceptionHandler(NoPermissionException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public Result noPermissionExceptionException(NoPermissionException e) {
        String str = "对不起，您没有权限";
        return Result.fail(HttpStatus.METHOD_NOT_ALLOWED.value(), str);
    }

    @ExceptionHandler(org.springframework.validation.BindException.class)
    @ResponseStatus(HttpStatus.PAYMENT_REQUIRED)
    public Result springBindExceptionException(org.springframework.validation.BindException e) {
        return Result.fail(HttpStatus.PAYMENT_REQUIRED.value(), e.getMessage());
    }

    @ExceptionHandler(APIException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result apiExceptionException(HttpServletResponse response, APIException e) {
        return Result.fail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result exceptionException(Exception e) {
        String errorMsg = "message->:" + e.getMessage();
        logger.error(errorMsg, e);

        return Result.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), errorMsg);
    }

    private List<Map<String, String>> parseValidMethod(MethodArgumentNotValidException e) {
        return e.getBindingResult().getAllErrors().stream().map(obj -> {
            Map<String, String> result = new HashMap<>(1);
            result.put(((FieldError) obj).getField(), obj.getDefaultMessage());
            return result;
        }).collect(Collectors.toList());
    }

}
