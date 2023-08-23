package com.wbxnl.blog.controller;

import com.wbxnl.blog.common.Result;
import com.wbxnl.blog.controller.base.AbstractController;
import com.wbxnl.blog.model.dto.RoleResourceDto;
import com.wbxnl.blog.model.entity.RoleResource;
import com.wbxnl.blog.model.vo.RoleResourceVo;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiImplicitParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.wbxnl.blog.service.RoleResourceService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 角色资源 前端控制器
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Slf4j
@RestController
@RequestMapping("/role/interface")
@Tag(name ="RoleResourceController" ,description = "角色和资源关系模块")
public class RoleResourceController extends AbstractController<RoleResourceService, RoleResource, RoleResourceDto, RoleResourceVo> {

    @Autowired
    private RoleResourceService roleResourceService;

    @GetMapping("/list/{roleId}")
    @Operation(summary = "根据角色id获取该角色拥有的资源编号集合")
    public Result getResourceOfRole(@Parameter(description = "角色编号") @PathVariable("roleId")Integer roleId){
        List<RoleResource> list = roleResourceService.lambdaQuery().eq(RoleResource::getRoleId, roleId).list();
        return new Result().ok(list);
    }
}
