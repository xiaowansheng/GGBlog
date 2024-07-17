package com.wbxnl.blog.domain.user.service.impl;

import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import com.wbxnl.blog.domain.user.model.aggregate.UserBaseInfoAggregate;
import com.wbxnl.blog.domain.user.model.aggregate.UserDetailAggregate;
import com.wbxnl.blog.domain.user.model.entity.EmailLoginEntity;
import com.wbxnl.blog.domain.user.model.entity.UserQueryEntity;
import com.wbxnl.blog.domain.user.model.entity.UserUpdateEntity;
import com.wbxnl.blog.domain.user.model.vo.UserRegisterVo;
import com.wbxnl.blog.domain.user.repository.IUserRepository;
import com.wbxnl.blog.domain.user.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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


    @Override
    public boolean register(UserRegisterVo userRegisterVo) {
        return userRepository.register(userRegisterVo);
    }

    @Override
    public UserBaseInfoAggregate login(EmailLoginEntity emailLoginEntity) {
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
}
