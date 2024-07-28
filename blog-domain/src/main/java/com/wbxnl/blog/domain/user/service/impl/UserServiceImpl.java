package com.wbxnl.blog.domain.user.service.impl;

import com.wbxnl.blog.common.enums.OperationCodeEnum;
import com.wbxnl.blog.common.exception.BlogException;
import com.wbxnl.blog.common.utils.HttpUtils;
import com.wbxnl.blog.common.utils.JwtUtil;
import com.wbxnl.blog.common.utils.ObjectConvertUtils;
import com.wbxnl.blog.common.utils.StringUtils;
import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import com.wbxnl.blog.domain.user.model.aggregate.UserBaseInfoAggregate;
import com.wbxnl.blog.domain.user.model.aggregate.UserDetailAggregate;
import com.wbxnl.blog.domain.user.model.aggregate.UserLoginDataAggregate;
import com.wbxnl.blog.domain.user.model.aggregate.UserLoginLogAggregate;
import com.wbxnl.blog.domain.user.model.entity.*;
import com.wbxnl.blog.domain.user.model.vo.LoginLogVo;
import com.wbxnl.blog.domain.user.model.vo.UserRegisterVo;
import com.wbxnl.blog.domain.user.repository.IUserRepository;
import com.wbxnl.blog.domain.user.service.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/16 23:06
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;

    private final HttpServletRequest request;


    @Override
    public boolean register(UserRegisterVo userRegisterVo) {
        // 获取验证码
        String verificationCode =userRepository.getVerificationCode(userRegisterVo.getUsername());
        if(verificationCode == null){
            throw new BlogException(OperationCodeEnum.VERIFICATION_CODE_ERROR);
        }
        if(!verificationCode.equalsIgnoreCase(userRegisterVo.getVerificationCode())){
            throw new BlogException(OperationCodeEnum.VERIFICATION_CODE_ERROR);
        }
        return userRepository.register(userRegisterVo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserLoginDataAggregate login(EmailLoginEntity emailLoginEntity) {
        // 查询账户和密码
        String userPassword=userRepository.getPassword(emailLoginEntity.getUsername());
        // 密码加密
        String encryptPassword = StringUtils.encrypt(emailLoginEntity.getPassword());
        // 验证账号和密码是否匹配
        if(!encryptPassword.equals(userPassword)){
            throw new BlogException(OperationCodeEnum.PASSWORD_ERROR);
        }
        // 获取用户信息
        UserBaseInfoAggregate baseInfoAggregate = userRepository.getUser(emailLoginEntity);
        // 添加登陆日志
        String ipAddress = HttpUtils.getIpAddress(request);
        LoginLogVo loginLogVo = LoginLogVo.builder()
                .ipAddress(ipAddress)
                .ipSource(HttpUtils.getIpSource(ipAddress))
                .device(HttpUtils.getRequestDevice(request))
                .browser(HttpUtils.getRequestBrowser(request))
                .build();
        userRepository.addLoginLog(loginLogVo);
        // 生成token
        UserLoginDataAggregate loginDataAggregate = ObjectConvertUtils.convert(baseInfoAggregate, UserLoginDataAggregate.class);
        String token = JwtUtil.getToken(baseInfoAggregate.getUsername(), new HashMap<>());
        loginDataAggregate.setToken(token);
        loginDataAggregate.setRefreshToken(JwtUtil.getRefreshToken(baseInfoAggregate.getUsername(),new HashMap<>()));
        loginDataAggregate.setTokenExpireTime(JwtUtil.getExpireTime(token));
        return loginDataAggregate;
    }

    @Override
    public TokenEntity freshToken(String oldToken, String refreshToken) {
        // 验证刷新token是否合法
        JwtUtil.checkToken(refreshToken);
        // 获取用户数据
        String username = JwtUtil.getUsername(refreshToken);
        Map<String, Object> inforMap = JwtUtil.getInforMap(oldToken);
        // 生成一个新token
        String token = JwtUtil.getToken(username, inforMap);
        TokenEntity tokenEntity = TokenEntity.builder()
                .token(token)
                .expireTime(JwtUtil.getExpireTime(token))
                .build();
        return tokenEntity;
    }

    @Override
    public boolean logout() {
        return userRepository.logout();
    }

    @Override
    public boolean updatePassword(UpdatePasswordEntity updatePasswordEntity) {
        String verificationCode1 = userRepository.getVerificationCode(updatePasswordEntity.getUsername());
        if(!verificationCode1.equalsIgnoreCase(updatePasswordEntity.getVerificationCode())){
            throw new BlogException(OperationCodeEnum.VERIFICATION_CODE_ERROR);
        }
        // 新密码加密
        String newPassword = StringUtils.encrypt(updatePasswordEntity.getNewPassword());
        // 更新密码
        return userRepository.updatePassword(updatePasswordEntity.getUsername(),newPassword);}

    @Override
    public boolean updateUserInfo(UserUpdateEntity userUpdateEntity) {
        return userRepository.updateUserInfo(userUpdateEntity);
    }

    @Override
    public boolean setUserStatus(Integer id, Integer disable) {
        return userRepository.setUserStatus(id, disable);
    }

    @Override
    public PageData<UserDetailAggregate> getPageUserDetails(PageParams pageParams, UserQueryEntity userQueryEntity) {
        return userRepository.getPageUserDetails(pageParams, userQueryEntity);
    }

    @Override
    public PageData<UserLoginLogAggregate> getPageUserLogins(PageParams pageParams, UserLoginLogQueryEntity userLoginLogQueryEntity) {
        return userRepository.getPageUserLogins(pageParams, userLoginLogQueryEntity);
    }
}
