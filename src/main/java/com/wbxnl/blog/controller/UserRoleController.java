package com.wbxnl.blog.controller;

import com.wbxnl.blog.controller.base.AbstractController;
import com.wbxnl.blog.model.dto.UserRoleDto;
import com.wbxnl.blog.model.entity.UserRole;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import com.wbxnl.blog.service.UserRoleService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户账号对应角色 前端控制器
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Slf4j
@RestController
@RequestMapping("/user/role")
@Tag(name = "UserRoleController",description = "用户角色模块")
public class UserRoleController extends AbstractController<UserRoleService, UserRole, UserRoleDto, UserRole> {

}
