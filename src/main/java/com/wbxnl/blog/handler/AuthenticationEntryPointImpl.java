package com.wbxnl.blog.handler;

import com.alibaba.fastjson2.JSON;
import com.wbxnl.blog.common.Result;
import com.wbxnl.blog.constant.params.HeaderParamConstant;
import com.wbxnl.blog.enums.OperationStateCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 处理认证异常，例如用户未登录访问需要登录的资源时的异常。
 * 用来解决匿名用户访问无权限资源时的异常
 *
 * @author wansheng
 * @createDate 2022/8/27 21:13
 */
@Component
@Slf4j
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        //设置响应头
        response.setContentType(HeaderParamConstant.RESPONSE_TYPE);

        log.info("AuthenticationEntryPointImpl：{}",authException.getMessage());
        //返回数据
//        log.info("异常信息:");
        authException.printStackTrace();

        Result result = null;
//        if(authException instanceof BadCredentialsException){
//            //BadCredentialsException 登录验证未通过
//            result=new Result<>().error(OperationStateCode.LOGIN_FAILURE);
//        }else
        if (authException instanceof InsufficientAuthenticationException) {
            //InsufficientAuthenticationException 访问的资源需要登录
            result = new Result<>().error(OperationStateCode.NO_LOGIN);
        }
        else {
            // 其它异常
            result = new Result<>().error(OperationStateCode.NO_RIGHT);
        }
        response.getWriter().write(JSON.toJSONString(result));
    }
}
