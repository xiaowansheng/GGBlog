package com.wbxnl.blog.controller;

import com.wbxnl.blog.annotation.Api;
import com.wbxnl.blog.common.PageData;
import com.wbxnl.blog.common.Result;
import com.wbxnl.blog.controller.base.AbstractController;
import com.wbxnl.blog.model.dto.LogErrorDto;
import com.wbxnl.blog.model.entity.LogError;
//import io.swagger.annotations.Api;
import com.wbxnl.blog.model.vo.params.ErrorLogParams;
import com.wbxnl.blog.model.vo.params.PageParams;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.wbxnl.blog.service.LogErrorService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 系统异常错误日志 前端控制器
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Slf4j
@RestController
@RequestMapping("/log/error")
@Tag(name = "LogErrorController",description = "错误日志模块")
public class LogErrorController extends AbstractController<LogErrorService, LogError, LogErrorDto,LogError> {

    @Autowired
    private LogErrorService logErrorService;

    @GetMapping("/page")
    public Result<PageData<LogErrorDto>> getPage(@ParameterObject PageParams pageParams,@ParameterObject ErrorLogParams errorLogParams){
        PageData<LogErrorDto> pageData = logErrorService.getPage(pageParams, errorLogParams);
        return new Result().ok(pageData);
    }
}
