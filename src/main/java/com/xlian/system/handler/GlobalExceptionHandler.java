package com.xlian.system.handler;

import com.xlian.common.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class) //该注解声明异常处理方法
    public Result exceptionHandler(Exception e) {
        log.error(e.getMessage(), e);
        return Result.error(e.getMessage());
    }
}
