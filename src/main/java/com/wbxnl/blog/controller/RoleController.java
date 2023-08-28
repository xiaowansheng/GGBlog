package com.wbxnl.blog.controller;

import com.wbxnl.blog.annotation.Log;
import com.wbxnl.blog.common.Result;
import com.wbxnl.blog.constant.types.OperationType;
import com.wbxnl.blog.controller.base.AbstractController;
import com.wbxnl.blog.model.dto.RoleDto;
import com.wbxnl.blog.model.dto.extra.NameLabelDto;
import com.wbxnl.blog.model.entity.Role;
import com.wbxnl.blog.model.vo.RoleVo;
import com.wbxnl.blog.model.vo.extra.StatusVo;
import com.wbxnl.blog.utils.ConvertUtils;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
import com.wbxnl.blog.validator.group.Update;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.wbxnl.blog.service.RoleService;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户角色 前端控制器
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Slf4j
@RestController
@RequestMapping("/role")
@Tag(name = "RoleController",description = "角色信息模块")
public class RoleController extends AbstractController<RoleService, Role, RoleDto, RoleVo> {

    @Autowired
    RoleService roleService;

    @GetMapping("/list")
    @Operation(summary = "获取全部的角色全部信息")
    public Result<List<RoleDto>> getAllRoles(){
        List<Role> list = roleService.list();
        List<RoleDto> dto = ConvertUtils.sourceToTarget(list, RoleDto.class);
        return new Result().ok(dto);
    }

    @GetMapping("/simple/list")
    @Operation(summary = "获取全部的角色简略信息")
    public Result<List<NameLabelDto>> getAllRolesSimple(){
        List<Role> roleList = roleService.lambdaQuery().select(Role::getId,Role::getName,Role::getLabel).list();
        List<NameLabelDto> nameLabelDtoList = roleList.stream().map(role -> new NameLabelDto(role.getName(), role.getLabel())).collect(Collectors.toList());
        return new Result().ok(nameLabelDtoList);
    }

    @PutMapping("/status")
    @Operation(summary = "改变角色的状态信息")
    @Log(type = OperationType.UPDATE,desc = "修改角色的禁用状态信息")
    public Result updateStatus(@Validated({Update.class}) @RequestBody StatusVo statusVo){
        roleService.lambdaUpdate().eq(Role::getId,statusVo.getId()).set(Role::getDisable,statusVo.getStatus()).update();
        return new Result();
    }
}
