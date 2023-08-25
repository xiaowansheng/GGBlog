package com.wbxnl.blog.controller;

import com.wbxnl.blog.common.PageData;
import com.wbxnl.blog.common.Result;
import com.wbxnl.blog.controller.base.AbstractController;
import com.wbxnl.blog.model.dto.LoginLogDto;
import com.wbxnl.blog.model.entity.LoginLog;
import com.wbxnl.blog.model.vo.params.LoginLogParams;
import com.wbxnl.blog.model.vo.params.PageParams;
import com.wbxnl.blog.service.LoginLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 登录日志，记录用户的登录信息 前端控制器
 * </p>
 *
 * @author wansheng
 * @since 2023-08-07
 */
@RestController
@RequestMapping("/log/login")
@Tag(name = "LoginLogController",description = "登录日志模块")
public class LoginLogController extends AbstractController<LoginLogService, LoginLog, LoginLogDto, LoginLog> {

    @Autowired
    private LoginLogService loginLogService;
    @GetMapping("/user")
    @Operation(summary = "用户查询自己的登录日志")
    public Result<PageData<LoginLogDto>> getLoginLogByUser(@ParameterObject PageParams pageParams){
        PageData<LoginLogDto> pageData= loginLogService.getLoginLogByUser(pageParams);
        return new Result().ok(pageData);
    }

    @GetMapping("/user/{id}")
    @Operation(summary = "根据用户id查询用户的登录日志")
    public Result<PageData<LoginLogDto>> getLoginLog(@ParameterObject PageParams pageParams,@Parameter(description = "用户编号") @PathVariable("id")Integer userAuthId){
       PageData<LoginLogDto> pageData= loginLogService.getLoginLog(pageParams,userAuthId);
       return new Result().ok(pageData);
    }

    @GetMapping("/page")
    @Operation(summary = "分页查询登录日志")
    public Result<PageData<LoginLogDto>> getPage(@ParameterObject PageParams pageParams, @ParameterObject LoginLogParams loginLogParams){
        PageData<LoginLogDto> pageData = loginLogService.getPage(pageParams, loginLogParams);
        return new Result().ok(pageData);
    }
}
