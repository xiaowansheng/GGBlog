package com.wbxnl.blog.api.admin.service;

import com.wbxnl.blog.api.admin.model.req.*;
import com.wbxnl.blog.api.admin.model.res.LoginDataRes;
import com.wbxnl.blog.api.admin.model.res.TokenDataRes;
import com.wbxnl.blog.api.admin.model.res.UserInfoDetailRes;
import com.wbxnl.blog.api.admin.model.res.UserLoginLogDetailRes;
import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/8/4 16:50
 */
public interface IUserService {

    /**
     * 注册
     * @param userRegisterReq 注册信息
     */
    void register(UserRegisterReq userRegisterReq);

    /**
     * 登录
     * @param userLoginReq 登录信息
     * @return 登录信息
     */
    LoginDataRes login(UserLoginReq userLoginReq);

    /**
     * 退出登录
     */
    void logout();

    /**
     * 更新用户信息
     * @param userUpdateDataReq 更新信息
     */
    void updateUserInfo(UserUpdateDataReq userUpdateDataReq);

    /**
     * 重置密码
     * @param userResetPasswordReq 重置密码信息
     */
    void resetPassword(UserResetPasswordReq userResetPasswordReq);

    /**
     * 禁用用户
     * @param id 用户id
     */
    void updateUserStatus(Integer id, Integer status);

    /**
     * 刷新token
     * @param refreshTokenReq 刷新token信息
     * @return token
     */
    TokenDataRes refreshToken(RefreshTokenReq refreshTokenReq);

    /**
     * 分页查询用户信息
     * @param pageParams 分页信息
     * @param userQueryReq 查询信息
     * @return 用户信息
     */
    PageData<UserInfoDetailRes> getPageUserDetails(PageParams pageParams, UserQueryReq userQueryReq);

    /**
     * 分页查询用户登录信息
     * @param pageParams 分页信息
     * @param userLoginLogQueryReq 查询信息
     * @return 用户登录信息
     */
    PageData<UserLoginLogDetailRes> getPageUserLogins(PageParams pageParams, UserLoginLogQueryReq userLoginLogQueryReq);

    /**
     * 根据用户名分页查询用户登录信息
     * @param pageParams 分页信息
     * @param username 用户名
     * @return 用户登录信息
     */
    PageData<UserLoginLogDetailRes> getPageUserLoginsByUsername(PageParams pageParams,String username);
}
