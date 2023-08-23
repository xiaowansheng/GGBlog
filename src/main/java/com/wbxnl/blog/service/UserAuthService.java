package com.wbxnl.blog.service;

import com.wbxnl.blog.common.PageData;
import com.wbxnl.blog.common.Result;
import com.wbxnl.blog.model.dto.UserAuthDto;
import com.wbxnl.blog.model.dto.UserDetailsDto;
import com.wbxnl.blog.model.entity.UserAuth;
import com.wbxnl.blog.model.vo.*;
import com.wbxnl.blog.model.vo.params.PageParams;
import com.wbxnl.blog.model.vo.params.UserAuthParams;
import com.wbxnl.blog.service.base.BaseService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;

/**
 * <p>
 * 用户账号 服务类
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
public interface UserAuthService extends BaseService< UserAuth, UserAuthDto, UserAuthVo> {

    Result login(LoginVo loginVo);

    Result logout();

    /**
     * 用户注册账号
     * @param signupVo
     * @return
     */
    Result signup(SignupVo signupVo);

    /**
     * 获取验证码
     * @param email
     * @return
     */
    String getVerificationCode(String email);

    /**
     * 重置密码
     * @param resetVo
     * @return
     */
    Result resetPassword(ResetVo resetVo);

    /**
     * 刷新token
     * @return
     */
    Result refreshToken(HttpServletRequest request);

    /**
     * 修改密码
     * @param passwordVo
     * @return
     */
    void updatePassword(PasswordVo passwordVo);
}
