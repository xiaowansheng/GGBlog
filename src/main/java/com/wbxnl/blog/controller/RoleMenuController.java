package com.wbxnl.blog.controller;

import com.wbxnl.blog.common.Result;
import com.wbxnl.blog.controller.base.AbstractController;
import com.wbxnl.blog.model.dto.RoleMenuDto;
import com.wbxnl.blog.model.dto.RouterDto;
import com.wbxnl.blog.model.entity.RoleMenu;
import com.wbxnl.blog.model.vo.RoleMenuVo;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiOperation;
import com.wbxnl.blog.utils.ConvertUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.wbxnl.blog.service.RoleMenuService;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * <p>
 * 角色菜单 前端控制器
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Slf4j
@RestController
@RequestMapping("/role/menu")
@Tag(name = "RoleMenuController",description = "角色菜单关系模块")
public class RoleMenuController extends AbstractController<RoleMenuService, RoleMenu, RoleMenuDto, RoleMenuVo> {

    @Autowired
    private RoleMenuService roleMenuService;

    @GetMapping("/list/{roleId}")
    @Operation(summary = "根据角色编号获取菜单权限信息")
    public Result<List<RoleMenuDto>> getMenuOfRole(@Parameter(description = "角色编号") @PathVariable("roleId") Integer roleId) {
//        List<RoleMenuDto> list =roleMenuService.getRoleMenus(roleId);
        List<RoleMenu> list = roleMenuService.lambdaQuery().select(RoleMenu::getRoleId,RoleMenu::getMenuId).eq(RoleMenu::getRoleId, roleId).list();
        List<RoleMenuDto> roleMenuDtos = ConvertUtils.sourceToTarget(list, RoleMenuDto.class);
        return new Result().ok(roleMenuDtos);
    }

    @GetMapping("/routes")
    @Operation(summary = "根据当前登录用户获取角色，根据角色查询路由菜单权限")
    public Result<List<RoleMenuDto>> getRoutes(){
        List<RouterDto> routers = roleMenuService.getRoutes();
        return new Result().ok(routers);
    }
}
