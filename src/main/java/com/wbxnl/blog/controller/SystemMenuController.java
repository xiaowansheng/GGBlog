package com.wbxnl.blog.controller;

import com.wbxnl.blog.annotation.Log;
import com.wbxnl.blog.common.Result;
import com.wbxnl.blog.constant.types.OperationType;
import com.wbxnl.blog.controller.base.AbstractController;
import com.wbxnl.blog.model.dto.SystemMenuDto;
import com.wbxnl.blog.model.entity.SystemMenu;
import com.wbxnl.blog.model.entity.SystemResource;
import com.wbxnl.blog.model.vo.SystemMenuVo;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
import com.wbxnl.blog.model.vo.extra.StatusVo;
import com.wbxnl.blog.validator.group.Update;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.wbxnl.blog.service.SystemMenuService;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * <p>
 * 菜单目录 前端控制器
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Slf4j
@RestController
@RequestMapping("/system/menu")
@Tag(name = "SystemMenuController",description = "系统菜单模块")
public class SystemMenuController extends AbstractController<SystemMenuService, SystemMenu, SystemMenuDto, SystemMenuVo> {

    @Autowired
    private SystemMenuService systemMenuService;

    @GetMapping("/tree")
    @Operation(summary = "获取菜单树形结构数据")
    public Result<List<SystemMenuDto>> getMenuTree(){
        List<SystemMenuDto> tree=systemMenuService.getMenuTree();
        return new Result<List<SystemMenuDto>>().ok(tree);
    }

    @GetMapping("/simple/tree")
    @Operation(summary = "获取菜单树形结构简单数据")
    public Result<List<SystemMenuDto>> getMenuSimpleTree(){
        List<SystemMenuDto> tree=systemMenuService.getMenuTree();
        return new Result<List<SystemMenuDto>>().ok(tree);
    }

    @PutMapping("/status")
    @Operation(summary = "改变菜单的隐藏状态信息")
    @Log(type = OperationType.UPDATE,desc = "修改菜单的隐藏状态")
    public Result updateStatus(@Validated({Update.class}) @RequestBody StatusVo statusVo){
        systemMenuService.lambdaUpdate().eq(SystemMenu::getId,statusVo.getId()).set(SystemMenu::getHidden,statusVo.getStatus()).update();
        return new Result();
    }
}
