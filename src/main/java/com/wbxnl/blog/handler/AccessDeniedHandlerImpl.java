package com.wbxnl.blog.handler;

import com.alibaba.fastjson2.JSON;
import com.wbxnl.blog.common.Result;
import com.wbxnl.blog.constant.params.HeaderParamConstant;
import com.wbxnl.blog.enums.OperationStateCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;


/**
 * 用户权限处理
 *  处理授权异常，例如已登录用户无权访问某个资源时的异常。
 * @author xiaowansheng
 * @date 2021/07/28
 */
@Component
@Slf4j
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {


    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException {
        httpServletResponse.setContentType(HeaderParamConstant.RESPONSE_TYPE);
        log.info("AccessDeniedHandlerImpl：{}",e.getMessage());
//        httpServletResponse.getWriter().write(JSON.toJSONString(new Result<>().error(OperationStateCode.AUTHENTICATION_FAILURE).setData(e.getMessage())));
        httpServletResponse.getWriter().write(JSON.toJSONString(new Result<>().error().setMessage(e.getMessage()).setData(e.getCause())));
    }

}
