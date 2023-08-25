package com.wbxnl.blog.controller;

import com.wbxnl.blog.annotation.Api;
import com.wbxnl.blog.common.PageData;
import com.wbxnl.blog.common.Result;
import com.wbxnl.blog.controller.base.AbstractController;
import com.wbxnl.blog.model.dto.VisitorDto;
import com.wbxnl.blog.model.entity.Visitor;
import com.wbxnl.blog.model.vo.params.PageParams;
import com.wbxnl.blog.model.vo.params.VisitorParams;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.wbxnl.blog.service.VisitorService;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 访客信息 前端控制器
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Slf4j
@RestController
@RequestMapping("/visitor")
@Tag(name = "VisitorController", description = "游客信息模块")
public class VisitorController extends AbstractController<VisitorService, Visitor, VisitorDto, Visitor> {
    @Autowired
    private VisitorService visitorService;

    @GetMapping("/page")
    @Operation(summary = "分页获取游客访问信息列表")
    public Result<PageData<VisitorDto>> getPage(@ParameterObject PageParams pageParams, @ParameterObject VisitorParams visitorParams){
        PageData<VisitorDto> pageData = visitorService.getPage(pageParams, visitorParams);
        return new Result().ok(pageData);
    }

}
