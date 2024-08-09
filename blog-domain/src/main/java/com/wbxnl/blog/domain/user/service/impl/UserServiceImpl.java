package com.wbxnl.blog.domain.user.service.impl;

import com.wbxnl.blog.common.enums.OperationCodeEnum;
import com.wbxnl.blog.common.exception.BlogException;
import com.wbxnl.blog.common.utils.*;
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
    @Transactional(rollbackFor = Exception.class)
    public void register(UserRegisterVo userRegisterVo) {
        // 获取验证码
        String verificationCode = userRepository.getVerificationCode(userRegisterVo.getEmail());
        if (verificationCode == null) {
            throw new BlogException(OperationCodeEnum.VERIFICATION_CODE_ERROR);
        }
        if (!verificationCode.equalsIgnoreCase(userRegisterVo.getVerificationCode())) {
            throw new BlogException(OperationCodeEnum.VERIFICATION_CODE_ERROR);
        }
        // 加密密码
        String encryptPassword = MyStringUtils.encrypt(userRegisterVo.getPassword());
        userRegisterVo.setPassword(encryptPassword);
        // 用户账户
        UserRegisterDataEntity userRegisterDataEntity = new UserRegisterDataEntity();
        userRegisterDataEntity.setUsername(userRegisterVo.getEmail());
        userRegisterDataEntity.setPassword(userRegisterVo.getPassword());
        userRegisterDataEntity.setEmail(userRegisterVo.getEmail());
        // 用户信息
        UserRegisterInfoEntity userRegisterInfoEntity = new UserRegisterInfoEntity();
        userRegisterInfoEntity.setEmail(userRegisterVo.getEmail());
        userRegisterInfoEntity.setNickname(userRegisterVo.getEmail());
        userRegisterInfoEntity.setUserInfoKey(UuidUtils.shortUuid());
        // 绑定账户和资料的关系
        userRegisterDataEntity.setUserInfoKey(userRegisterInfoEntity.getUserInfoKey());
        // 添加用户账户
        boolean addUserAuth = userRepository.addUserAuth(userRegisterDataEntity);
        // 添加用户资料
        boolean addUserInfo = userRepository.addUserInfo(userRegisterInfoEntity);
        if (!addUserAuth || !addUserInfo) {
            throw new BlogException(OperationCodeEnum.REGISTER_FAILURE);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserLoginDataAggregate login(EmailLoginEntity emailLoginEntity) {
        // 查询账户和密码
        String userPassword = userRepository.getPassword(emailLoginEntity.getUsername());
        // 密码加密
        String encryptPassword = MyStringUtils.encrypt(emailLoginEntity.getPassword());
        // 验证账号和密码是否匹配
        if (!encryptPassword.equals(userPassword)) {
            throw new BlogException(OperationCodeEnum.PASSWORD_ERROR);
        }
        // 判断用户是否可用
        boolean userStatus = userRepository.checkUserAvailableStatus(emailLoginEntity.getUsername());
        if (!userStatus) {
            throw new BlogException(OperationCodeEnum.USER_DISABLE);
        }
        // 获取用户信息
        UserBaseInfoAggregate baseInfoAggregate = userRepository.getUser(emailLoginEntity.getUsername());
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
        loginDataAggregate.setRefreshToken(JwtUtil.getRefreshToken(baseInfoAggregate.getUsername(), new HashMap<>()));
        loginDataAggregate.setTokenExpireTime(JwtUtil.getExpireTime(token));
        return loginDataAggregate;
    }

    @Override
    public TokenEntity refreshToken(String oldToken, String refreshToken) {
        // 验证刷新token是否合法
        JwtUtil.checkToken(refreshToken);
        // 获取用户数据
        String username = JwtUtil.getUsername(refreshToken);
        Map<String, Object> inforMap = JwtUtil.getInforMap(oldToken);
        // 生成一个新token
        String token = JwtUtil.getToken(username, inforMap);
        String newRefreshToken = JwtUtil.getRefreshToken(username, inforMap);
        return TokenEntity.builder()
                .token(token)
                .expireTime(JwtUtil.getExpireTime(token))
                .refreshToken(newRefreshToken)
                .build();
    }

    @Override
    public void logout(String username) {
        boolean logout = userRepository.logout(username);
        if (!logout) {
            throw new BlogException(OperationCodeEnum.FAILURE);
        }
    }

    @Override
    public void updatePassword(UpdatePasswordEntity updatePasswordEntity) {
        String verificationCode1 = userRepository.getVerificationCode(updatePasswordEntity.getUsername());
        if (!verificationCode1.equalsIgnoreCase(updatePasswordEntity.getVerificationCode())) {
            throw new BlogException(OperationCodeEnum.VERIFICATION_CODE_ERROR);
        }
        // 新密码加密
        String newPassword = MyStringUtils.encrypt(updatePasswordEntity.getNewPassword());
        // 更新密码
        boolean updated = userRepository.updatePassword(updatePasswordEntity.getUsername(), newPassword);
        if (!updated) {
            throw new BlogException(OperationCodeEnum.UPDATE_FAILURE);
        }
    }

    @Override
    public void updateUserInfo(UserUpdateEntity userUpdateEntity) {
        boolean updated = userRepository.updateUserInfo(userUpdateEntity);
        if (!updated) {
            throw new BlogException(OperationCodeEnum.UPDATE_FAILURE);
        }
    }

    @Override
    public void setUserStatus(Integer id, Integer disable) {
        boolean userStatus = userRepository.setUserStatus(id, disable);
        if (!userStatus) {
            throw new BlogException(OperationCodeEnum.UPDATE_FAILURE);
        }
    }

    @Override
    public PageData<UserDetailAggregate> getPageUserDetails(PageParams pageParams, UserQueryEntity userQueryEntity) {
        return userRepository.getPageUserDetails(pageParams, userQueryEntity);
    }

    @Override
    public PageData<UserLoginLogAggregate> getPageUserLogins(PageParams pageParams, UserLoginLogQueryEntity userLoginLogQueryEntity) {
        return userRepository.getPageUserLoginLog(pageParams, userLoginLogQueryEntity);
    }
}
