package com.wbxnl.blog.controller;

import com.wbxnl.blog.common.Result;
import com.wbxnl.blog.controller.base.AbstractController;
import com.wbxnl.blog.model.dto.SystemResourceDto;
import com.wbxnl.blog.model.entity.SystemResource;
import com.wbxnl.blog.model.vo.SystemResourceVo;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.wbxnl.blog.service.SystemResourceService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/system/interface")
@Tag(name = "SystemResourceController",description = "系统资源模块")
public class SystemResourceController extends AbstractController<SystemResourceService, SystemResource, SystemResourceDto, SystemResourceVo> {

    @Autowired
    private SystemResourceService systemResourceService;
    @GetMapping("/tree")
    @Operation(summary = "获取接口资源树形结构数据")
    public Result getMenuTree(){
        List<SystemResourceDto> tree= systemResourceService.getResourceTree();
        return new Result<List<SystemResourceDto>>().ok(tree);
    }
}
