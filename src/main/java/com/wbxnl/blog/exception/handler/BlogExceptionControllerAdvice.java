package com.wbxnl.blog.exception.handler;

import com.wbxnl.blog.common.Result;
import com.wbxnl.blog.enums.OperationStateCode;
import com.wbxnl.blog.exception.BlogException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.BindException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 集中处理所有异常
 * @author wansheng
 * @createDate 2022/8/15 5:39
 */
@Slf4j
@ResponseBody
//@RestControllerAdvice(basePackages = "com.wbxnl.blog.**.*")
@RestControllerAdvice
public class BlogExceptionControllerAdvice {
    /**
     * 处理请求体数据校验异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result handlerValidException(MethodArgumentNotValidException e){
        Map<String,String> resultMap=new HashMap<>();
        e.getFieldErrors().forEach((err)->{
            log.info(err.getField()+":"+err.getDefaultMessage());
            resultMap.put(err.getField(),err.getDefaultMessage());
        });
        return new Result().error(OperationStateCode.ARGUMENT_NOT_VALID).setData(resultMap);
    }

    /**
     * 处理请求参数数据校验异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handlerValidException(ConstraintViolationException e){
        Map<String,String> resultMap=new HashMap<>();
        for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
            // 注意：需要转义 "."，所以用 "\\." ！！！！
            String[] split = violation.getPropertyPath().toString().split("\\.");
            log.info(split[split.length-1]+":"+ violation.getMessage());
            resultMap.put(split[split.length-1], violation.getMessage());
        }
        return new Result().error(OperationStateCode.ARGUMENT_NOT_VALID).setData(resultMap);
    }

    /**
     * 处理自定义异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = BlogException.class)
    public Result handlerBlogException(BlogException e){
        return new Result().error(e.code, e.message);
    }

    /**
     * 处理请求方法不支持异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public Result handlerHttpRequestMethodNoSupportedException(HttpRequestMethodNotSupportedException e){
        return new Result().error(OperationStateCode.METHOD_NOT_SUPPORTED);
    }

    /**
     * 处理请求方法不支持异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public Result handlerHttpRequestMethodNoSupportedException(MissingServletRequestParameterException e){
        return new Result().error(OperationStateCode.MISSING_PARAMETER);
    }

    /**
     * 处理其它所有异常（无法精确匹配异常时，调用）
     * @param e
     * @return
     */
    @ExceptionHandler(value = Throwable.class)
    public Result handlerValidException(Throwable e){
        log.error("操作执行出现异常！");
        // 打印异常调用栈
        e.printStackTrace();
        return new Result().error(OperationStateCode.FAILURE).setData(e.getCause());
    }
}
