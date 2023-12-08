package com.wbxnl.blog.controller;

import com.wbxnl.blog.common.PageData;
import com.wbxnl.blog.common.Result;
import com.wbxnl.blog.controller.base.AbstractController;
import com.wbxnl.blog.model.dto.LogOperationDto;
import com.wbxnl.blog.model.entity.LogOperation;
import com.wbxnl.blog.model.vo.params.OperationLogParams;
import com.wbxnl.blog.model.vo.params.PageParams;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.wbxnl.blog.service.LogOperationService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 操作日志 前端控制器
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Slf4j
@RestController
@RequestMapping("/log/operation")
@Tag( name = "LogOperationController",description = "操作日志模块")
public class LogOperationController extends AbstractController<LogOperationService, LogOperation, LogOperationDto,LogOperation> {
    @Autowired
    LogOperationService logOperationService;


    @GetMapping("/page")
    @Operation(summary = "分页获取操作日志信息")
    public Result<PageData<LogOperationDto>> getPage(@ParameterObject PageParams pageParams, @ParameterObject OperationLogParams operationLogParams){
        PageData<LogOperationDto> pageData = logOperationService.getPage(pageParams, operationLogParams);
        return new Result().ok(pageData);
    }
}
