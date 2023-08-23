package com.wbxnl.blog.controller;

import com.wbxnl.blog.controller.base.AbstractController;
import com.wbxnl.blog.model.dto.UserInfoDto;
import com.wbxnl.blog.model.entity.UserInfo;
import com.wbxnl.blog.model.vo.UserInfoVo;
//import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import com.wbxnl.blog.service.UserInfoService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

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

//    @Autowired
//    UserInfoService userInfoService;

    // 用不到
//    @GetMapping("")
//    @Operation(summary = "获取当前用户的信息")
//    public Result getInfo(){
//        //获取当前用户id
//        Integer userInfoId = SecurityUtils.getUserInfoId();
//        UserInfoDto dto = userInfoService.getDto(userInfoId);
//        return new Result<>().ok(dto);
//    }
}
