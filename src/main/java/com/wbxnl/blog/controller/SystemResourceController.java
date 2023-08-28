package com.wbxnl.blog.controller;

import com.wbxnl.blog.annotation.Log;
import com.wbxnl.blog.common.Result;
import com.wbxnl.blog.constant.types.OperationType;
import com.wbxnl.blog.controller.base.AbstractController;
import com.wbxnl.blog.model.dto.SystemResourceDto;
import com.wbxnl.blog.model.entity.Role;
import com.wbxnl.blog.model.entity.SystemResource;
import com.wbxnl.blog.model.vo.SystemResourceVo;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
import com.wbxnl.blog.model.vo.extra.StatusVo;
import com.wbxnl.blog.validator.group.Update;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.wbxnl.blog.service.SystemResourceService;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * <p>
 * 资源菜单 前端控制器
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Slf4j
@RestController
@RequestMapping("/system/resource")
@Tag(name = "SystemResourceController",description = "系统资源模块")
public class SystemResourceController extends AbstractController<SystemResourceService, SystemResource, SystemResourceDto, SystemResourceVo> {

    @Autowired
    private SystemResourceService systemResourceService;
    @GetMapping("/simple/tree")
    @Operation(summary = "获取接口资源树形结构简单数据")
    public Result<List<SystemResourceDto>> getMenuSimpleTree(){
        List<SystemResourceDto> tree= systemResourceService.getResourceSimpleTree();
        return new Result<List<SystemResourceDto>>().ok(tree);
    }

    @GetMapping("/tree")
    @Operation(summary = "获取接口资源树形结构数据")
    public Result<List<SystemResourceDto>> getMenuTree(){
        List<SystemResourceDto> tree= systemResourceService.getResourceTree();
        return new Result<List<SystemResourceDto>>().ok(tree);
    }

    @PutMapping("/status")
    @Operation(summary = "改变资源的开放状态信息")
    @Log(type = OperationType.UPDATE,desc = "修改资源的开放状态")
    public Result updateStatus(@Validated({Update.class}) @RequestBody StatusVo statusVo){
        systemResourceService.lambdaUpdate().eq(SystemResource::getId,statusVo.getId()).set(SystemResource::getOpen,statusVo.getStatus()).update();
        return new Result();
    }
}
