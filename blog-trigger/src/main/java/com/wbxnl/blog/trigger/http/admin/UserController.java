package com.wbxnl.blog.trigger.http.admin;

import com.wbxnl.blog.api.admin.model.req.*;
import com.wbxnl.blog.api.admin.model.res.LoginDataRes;
import com.wbxnl.blog.api.admin.model.res.TokenDataRes;
import com.wbxnl.blog.api.admin.model.res.UserInfoDetailRes;
import com.wbxnl.blog.api.admin.model.res.UserLoginLogDetailRes;
import com.wbxnl.blog.api.admin.service.IUserService;
import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
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

    }

    @Override
    public LoginDataRes login(UserLoginReq userLoginReq) {
        return null;
    }

    @Override
    public void logout() {

    }

    @Override
    public void updateUserInfo(UserUpdateDataReq userUpdateDataReq) {

    }

    @Override
    public void resetPassword(UserResetPasswordReq userResetPasswordReq) {

    }

    @Override
    public void updateUserStatus(Integer id, Integer status) {

    }

    @Override
    public TokenDataRes refreshToken(RefreshTokenReq refreshTokenReq) {
        return null;
    }

    @Override
    public PageData<UserInfoDetailRes> getPageUserDetails(PageParams pageParams, UserQueryReq userQueryReq) {
        return null;
    }

    @Override
    public PageData<UserLoginLogDetailRes> getPageUserLogins(PageParams pageParams, UserLoginLogQueryReq userLoginLogQueryReq) {
        return null;
    }

    @Override
    public PageData<UserLoginLogDetailRes> getPageUserLoginsByUsername(PageParams pageParams, String username) {
        return null;
    }
}
