package com.wbxnl.blog.controller;

import com.wbxnl.blog.annotation.Log;
import com.wbxnl.blog.common.PageData;
import com.wbxnl.blog.common.Result;
import com.wbxnl.blog.constant.types.OperationType;
import com.wbxnl.blog.controller.base.AbstractController;
import com.wbxnl.blog.enums.LoginTypeEmus;
import com.wbxnl.blog.enums.OperationStateCode;
import com.wbxnl.blog.exception.BlogException;
import com.wbxnl.blog.model.dto.extra.NameLabelDto;
import com.wbxnl.blog.model.dto.UserAuthDto;
import com.wbxnl.blog.model.dto.UserDetailsDto;
import com.wbxnl.blog.model.entity.UserAuth;
import com.wbxnl.blog.model.vo.*;
import com.wbxnl.blog.model.vo.params.PageParams;
import com.wbxnl.blog.model.vo.params.UserAuthParams;
import com.wbxnl.blog.utils.*;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;
//import io.swagger.annotations.ApiOperation;
import com.wbxnl.blog.validator.custom.CustomEmail;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.wbxnl.blog.service.UserAuthService;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户账号 前端控制器
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Slf4j
@RestController
@RequestMapping("/user/auth")
@Tag(name = "UserAuthController", description = "用户账号模块")
@Validated // 必须添加 @Validated 注解
public class UserAuthController extends AbstractController<UserAuthService, UserAuth, UserAuthDto, UserAuthVo> {

    @Autowired
    UserAuthService userAuthService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/page")
    @Operation(summary = "分页查询用户详细信息")
    public Result<PageData<UserAuthDto>> getUserList(@Validated @ParameterObject PageParams pageParams,@ParameterObject UserAuthParams userAuthParams) {
        PageData<UserAuthDto> pageData = userAuthService.getPage(pageParams, userAuthParams);
        return new Result<PageData<UserAuthDto>>().ok(pageData);
    }

    @PostMapping("/login")
    @Operation(summary = "用户登录")
    @Log(type = OperationType.LOGIN, desc = "用户登录系统")
    public Result login(@RequestBody @Validated LoginVo loginVo) {
        return userAuthService.login(loginVo);
    }

    @GetMapping("/logout")
    @Operation(summary = "用户注销")
    @Log(type = OperationType.LOGOUT, desc = "用户退出登录状态")
    public Result logout() {
        return userAuthService.logout();
    }

    @PutMapping("/password")
    @Operation(summary = "修改当前用户密码")
    @Log(type = OperationType.UPDATE, desc = "修改密码")
    public Result updatePassword(@RequestBody @Validated PasswordVo passwordVo) {
        userAuthService.updatePassword(passwordVo);
        return new Result();
    }


    @GetMapping("")
    @Operation(summary = "查询当前登录用户账号信息")
    public Result<UserAuthDto> getUserAuth() {
        UserAuthDto dto = userAuthService.getDto(SecurityUtils.getUserAuthId());
        return new Result().ok(dto);
    }

    @GetMapping("/login/type")
    @Operation(summary = "获取所有的登录类型")
    public Result<List<NameLabelDto>> getAllLoginType() {
        LoginTypeEmus[] typeEmus = LoginTypeEmus.values();
        List<NameLabelDto> mapList = Arrays.stream(typeEmus)
                .map(loginTypeEmus -> new NameLabelDto(loginTypeEmus.getName(), loginTypeEmus.getLabel()))
                .collect(Collectors.toList());
        return new Result().ok(mapList);
    }


    @GetMapping("/refresh/token")
    @Operation(summary = "刷新token")
    public Result refreshToken(HttpServletRequest request) {
        return userAuthService.refreshToken(request);
    }

    @GetMapping("/verificationCode")
    @Operation(summary = "获取验证码")
    public Result verificationCode(
            @Parameter(description = "接收验证码的邮箱")
            @NotBlank(message = "邮箱不能为空")
            @Email(message = "邮箱不合法")
            @RequestParam("email")
            String email) {
        userAuthService.getVerificationCode(email);
        return new Result();
    }

    @PostMapping("/signup")
    @Log(type = OperationType.ADD, desc = "用户注册")
    @Operation(summary = "用户注册")
    public Result signup(@RequestBody @Validated SignupVo signupVo) {
        return userAuthService.signup(signupVo);
    }

    @PutMapping("/reset/password")
    @Log(type = OperationType.UPDATE, desc = "重置密码")
    @Operation(summary = "重置密码")
    public Result resetPassword(@RequestBody @Validated ResetVo resetVo) {
        return userAuthService.resetPassword(resetVo);
    }
}
