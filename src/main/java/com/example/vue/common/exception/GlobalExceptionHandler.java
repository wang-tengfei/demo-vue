package com.example.vue.common.exception;

import com.example.vue.common.constant.Result;
import com.example.vue.common.constant.ResultEnum;
import com.example.vue.common.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.List;

/**
 * @author: Tengfei Wang
 * @description:
 * @date: Created in 14:31 2019-06-12
 * @modified by:
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

 /*   @ExceptionHandler(value = Exception.class)
    public Result exception(Exception e){
        log.error("exception: {}", e.getMessage());
        return ResultUtil.error(ResultEnum.SERVER_ERROR);
    }*/

    @ExceptionHandler(value = CustomerException.class)
    public Result exception(CustomerException e){
        log.error("exception: {}", e.getMessage());
        return ResultUtil.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public Result exception(HttpRequestMethodNotSupportedException e){
        log.error("exception: {}", e.getMessage());
        return ResultUtil.error(-1, e.getMessage());
    }

    @ExceptionHandler(value = NoHandlerFoundException.class)
    public Result noHandlerFoundException(NoHandlerFoundException e){
        log.error("exception: {}", e.getMessage());
        return ResultUtil.error(-1, e.getMessage());
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public Result exception(MissingServletRequestParameterException e){
        log.error("exception: {}", e.getMessage());
        return ResultUtil.error(-1, e.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result exception(MethodArgumentNotValidException e){
        log.error("exception: {}", e.getMessage());
        BindingResult bindingResult = e.getBindingResult();
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        StringBuilder sb = new StringBuilder();
        for (ObjectError error : allErrors) {
            String defaultMessage = error.getDefaultMessage();
            sb.append(defaultMessage).append(", ");
        }
        return ResultUtil.error(-1, sb.toString());
    }

}
