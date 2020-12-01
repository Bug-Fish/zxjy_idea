package com.zjy.servicebase.exceptionhandler;

import com.zjy.commonutils.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public R error(Exception e) {
        e.printStackTrace();
        return R.error().message("执行了全局异常处理……");
    }

    @ResponseBody
    @ExceptionHandler(ArithmeticException.class)
    public R error(ArithmeticException e) {
        e.printStackTrace();
        return R.error().message("执行了ArithmeticException异常处理……");
    }
    @ResponseBody
    @ExceptionHandler(ZjyException.class)
    public R error(ZjyException e) {
        e.printStackTrace();
        return R.error().code(e.getCode()).message(e.getMsg());
    }
}
