package com.wbxnl.blog.domain.user.service.impl;

import com.wbxnl.blog.common.utils.HttpUtils;
import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import com.wbxnl.blog.domain.user.model.aggregate.UserBaseInfoAggregate;
import com.wbxnl.blog.domain.user.model.aggregate.UserDetailAggregate;
import com.wbxnl.blog.domain.user.model.aggregate.UserLoginLoginAggregate;
import com.wbxnl.blog.domain.user.model.entity.EmailLoginEntity;
import com.wbxnl.blog.domain.user.model.entity.UserLoginLogQueryEntity;
import com.wbxnl.blog.domain.user.model.entity.UserQueryEntity;
import com.wbxnl.blog.domain.user.model.entity.UserUpdateEntity;
import com.wbxnl.blog.domain.user.model.vo.LoginLogVo;
import com.wbxnl.blog.domain.user.model.vo.UserRegisterVo;
import com.wbxnl.blog.domain.user.repository.IUserRepository;
import com.wbxnl.blog.domain.user.service.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        return userRepository.register(userRegisterVo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserBaseInfoAggregate login(EmailLoginEntity emailLoginEntity) {
        // 添加登陆日志
        String ipAddress = HttpUtils.getIpAddress(request);
        LoginLogVo loginLogVo = LoginLogVo.builder()
                .ipAddress(ipAddress)
                .ipSource(HttpUtils.getIpSource(ipAddress))
                .device(HttpUtils.getRequestDevice(request))
                .browser(HttpUtils.getRequestBrowser(request))
                .build();
        userRepository.addLoginLog(loginLogVo);
        return userRepository.login(emailLoginEntity);
    }

    @Override
    public boolean logout() {
        return userRepository.logout();
    }

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
    public PageData<UserLoginLoginAggregate> getPageUserLogins(PageParams pageParams, UserLoginLogQueryEntity userLoginLogQueryEntity) {
        return userRepository.getPageUserLogins(pageParams, userLoginLogQueryEntity);
    }
}
