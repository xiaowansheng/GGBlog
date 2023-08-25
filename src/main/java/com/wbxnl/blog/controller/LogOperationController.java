package com.wbxnl.blog.controller;

import com.wbxnl.blog.annotation.Api;
import com.wbxnl.blog.common.PageData;
import com.wbxnl.blog.common.Result;
import com.wbxnl.blog.controller.base.AbstractController;
import com.wbxnl.blog.model.dto.LogErrorDto;
import com.wbxnl.blog.model.dto.LogOperationDto;
import com.wbxnl.blog.model.entity.LogOperation;
//import io.swagger.annotations.Api;
import com.wbxnl.blog.model.vo.params.ErrorLogParams;
import com.wbxnl.blog.model.vo.params.OperationLogParams;
import com.wbxnl.blog.model.vo.params.PageParams;
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
@Api( "操作日志")
public class LogOperationController extends AbstractController<LogOperationService, LogOperation, LogOperationDto,LogOperation> {
    @Autowired
    LogOperationService logOperationService;


    @GetMapping("/page")
    public Result<PageData<LogOperationDto>> getPage(@ParameterObject PageParams pageParams, @ParameterObject OperationLogParams operationLogParams){
        PageData<LogOperationDto> pageData = logOperationService.getPage(pageParams, operationLogParams);
        return new Result().ok(pageData);
    }
}
