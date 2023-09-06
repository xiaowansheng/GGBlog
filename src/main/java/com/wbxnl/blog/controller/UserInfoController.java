package com.wbxnl.blog.controller;

import com.wbxnl.blog.annotation.Log;
import com.wbxnl.blog.common.Result;
import com.wbxnl.blog.constant.types.OperationType;
import com.wbxnl.blog.controller.base.AbstractController;
import com.wbxnl.blog.model.dto.UserInfoDto;
import com.wbxnl.blog.model.entity.UserInfo;
import com.wbxnl.blog.model.vo.UserInfoVo;
//import io.swagger.annotations.Api;
import com.wbxnl.blog.utils.SecurityUtils;
import com.wbxnl.blog.validator.group.Update;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.wbxnl.blog.service.UserInfoService;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Slf4j
@RestController
@RequestMapping("/user/info")
@Tag(name = "UserInfoController",description = "用户信息模块")
public class UserInfoController extends AbstractController<UserInfoService, UserInfo, UserInfoDto, UserInfoVo> {

    @Autowired
    UserInfoService userInfoService;

    @GetMapping("")
    @Operation(summary = "获取当前用户的信息")
    public Result<UserInfoDto> getInfo(){
        //获取当前用户id
        Integer userInfoId = SecurityUtils.getUserInfoId();
        UserInfoDto dto = userInfoService.getDto(userInfoId);
        return new Result().ok(dto);
    }


    @PutMapping("/person")
    @Operation(summary = "修改当前用户的个人信息")
    @Log(type = OperationType.UPDATE,desc = "修改当前用户的个人信息")
    public Result updateInformation(@Validated({Update.class}) @RequestBody UserInfoVo userInfoVo){
        //获取当前用户id
        Integer userInfoId = SecurityUtils.getUserInfoId();
        userInfoVo.setId(SecurityUtils.getUserInfoId());
        userInfoService.update(userInfoVo);
        return new Result();
    }
}
