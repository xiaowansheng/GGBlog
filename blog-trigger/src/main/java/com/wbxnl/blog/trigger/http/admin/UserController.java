package com.wbxnl.blog.trigger.http.admin;

import com.wbxnl.blog.api.admin.model.req.*;
import com.wbxnl.blog.api.admin.model.res.LoginDataRes;
import com.wbxnl.blog.api.admin.model.res.TokenDataRes;
import com.wbxnl.blog.api.admin.model.res.UserInfoDetailRes;
import com.wbxnl.blog.api.admin.model.res.UserLoginLogDetailRes;
import com.wbxnl.blog.api.admin.service.IUserService;
import com.wbxnl.blog.common.utils.ObjectConvertUtils;
import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import com.wbxnl.blog.domain.user.model.aggregate.UserDetailAggregate;
import com.wbxnl.blog.domain.user.model.aggregate.UserLoginDataAggregate;
import com.wbxnl.blog.domain.user.model.aggregate.UserLoginLogAggregate;
import com.wbxnl.blog.domain.user.model.entity.*;
import com.wbxnl.blog.domain.user.model.vo.UserRegisterVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/8/6 11:23
 */
@Slf4j
@RestController
@RequestMapping("/admin/user")
@RequiredArgsConstructor
public class UserController implements IUserService {

    private final com.wbxnl.blog.domain.user.service.IUserService userService;

    @Override
    public void register(UserRegisterReq userRegisterReq) {
        UserRegisterVo registerVo = ObjectConvertUtils.convert(userRegisterReq, UserRegisterVo.class);
        userService.register(registerVo);
    }

    @Override
    public LoginDataRes login(UserLoginReq userLoginReq) {
        EmailLoginEntity emailLoginEntity = ObjectConvertUtils.convert(userLoginReq, EmailLoginEntity.class);
        UserLoginDataAggregate loginDataAggregate = userService.login(emailLoginEntity);
        return ObjectConvertUtils.convert(loginDataAggregate, LoginDataRes.class);
    }

    @Override
    public void logout() {
        // TODO 获取当前用户信息
        String username="";
        userService.logout(username);
    }

    @Override
    public void updateUserInfo(UserUpdateDataReq userUpdateDataReq) {
        UserUpdateEntity updateEntity = ObjectConvertUtils.convert(userUpdateDataReq, UserUpdateEntity.class);
        userService.updateUserInfo(updateEntity);
    }

    @Override
    public void resetPassword(UserResetPasswordReq userResetPasswordReq) {
        UpdatePasswordEntity updatePassword = ObjectConvertUtils.convert(userResetPasswordReq, UpdatePasswordEntity.class);
        updatePassword.setUsername(userResetPasswordReq.getEmail());
        userService.updatePassword(updatePassword);
    }

    @Override
    public void updateUserStatus(Integer id, Integer status) {
        userService.updateUserStatus(id, status);
    }

    @Override
    public TokenDataRes refreshToken(RefreshTokenReq refreshTokenReq) {
        String refreshToken = refreshTokenReq.getRefreshToken();
        // TODO 获取当前token
        String oldToken = "";
        TokenEntity tokenEntity = userService.refreshToken(oldToken, refreshToken);
        return ObjectConvertUtils.convert(tokenEntity, TokenDataRes.class);
    }

    @Override
    public PageData<UserInfoDetailRes> getPageUserDetails(PageParams pageParams, UserQueryReq userQueryReq) {
        UserQueryEntity userQueryEntity = ObjectConvertUtils.convert(userQueryReq, UserQueryEntity.class);
        PageData<UserDetailAggregate> pageOfUserDetails = userService.getPageOfUserDetails(pageParams, userQueryEntity);
        return PageData.<UserInfoDetailRes>builder()
                .number(pageOfUserDetails.getNumber())
                .size(pageOfUserDetails.getSize())
                .total(pageOfUserDetails.getTotal())
                .data(ObjectConvertUtils.convertList(pageOfUserDetails.getData(), UserInfoDetailRes.class))
                .build();
    }

    @Override
    public PageData<UserLoginLogDetailRes> getPageUserLogins(PageParams pageParams, UserLoginLogQueryReq userLoginLogQueryReq) {
        UserLoginLogQueryEntity loginLogQueryEntity = ObjectConvertUtils.convert(userLoginLogQueryReq, UserLoginLogQueryEntity.class);
        PageData<UserLoginLogAggregate> pageOfUserLogins = userService.getPageOfUserLogins(pageParams, loginLogQueryEntity);
        return PageData.<UserLoginLogDetailRes>builder()
                .number(pageOfUserLogins.getNumber())
                .size(pageOfUserLogins.getSize())
                .total(pageOfUserLogins.getTotal())
                .data(ObjectConvertUtils.convertList(pageOfUserLogins.getData(), UserLoginLogDetailRes.class))
                .build();
    }

    @Override
    public PageData<UserLoginLogDetailRes> getPageOfUserLoginsByUsername(PageParams pageParams, String username) {
        PageData<UserLoginLogAggregate> userLoginLogs=userService.getPageOfUserLoginsByUsername(pageParams, username);
        return PageData.<UserLoginLogDetailRes>builder()
                .number(userLoginLogs.getNumber())
                .size(userLoginLogs.getSize())
                .total(userLoginLogs.getTotal())
                .data(ObjectConvertUtils.convertList(userLoginLogs.getData(), UserLoginLogDetailRes.class))
                .build();
    }
}
