package com.wbxnl.blog.infrastructure.persistent.repository;

import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import com.wbxnl.blog.domain.user.model.aggregate.UserBaseInfoAggregate;
import com.wbxnl.blog.domain.user.model.aggregate.UserDetailAggregate;
import com.wbxnl.blog.domain.user.model.aggregate.UserLoginLogAggregate;
import com.wbxnl.blog.domain.user.model.entity.EmailLoginEntity;
import com.wbxnl.blog.domain.user.model.entity.UserLoginLogQueryEntity;
import com.wbxnl.blog.domain.user.model.entity.UserQueryEntity;
import com.wbxnl.blog.domain.user.model.entity.UserUpdateEntity;
import com.wbxnl.blog.domain.user.model.vo.LoginLogVo;
import com.wbxnl.blog.domain.user.model.vo.UserRegisterVo;
import com.wbxnl.blog.domain.user.repository.IUserRepository;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/28 20:33
 */
public class UserRepository implements IUserRepository {

    @Override
    public boolean register(UserRegisterVo userRegisterVo) {
        return false;
    }

    @Override
    public UserBaseInfoAggregate getUser(EmailLoginEntity emailLoginEntity) {
        return null;
    }

    @Override
    public boolean logout() {
        return false;
    }

    @Override
    public boolean updateUserInfo(UserUpdateEntity userUpdateEntity) {
        return false;
    }

    @Override
    public boolean setUserStatus(Integer id, Integer disable) {
        return false;
    }

    @Override
    public PageData<UserDetailAggregate> getPageUserDetails(PageParams pageParams, UserQueryEntity userQueryEntity) {
        return null;
    }

    @Override
    public boolean addLoginLog(LoginLogVo loginLogVo) {
        return false;
    }

    @Override
    public PageData<UserLoginLogAggregate> getPageUserLogins(PageParams pageParams, UserLoginLogQueryEntity userLoginLogQueryEntity) {
        return null;
    }

    @Override
    public String getVerificationCode(String email) {
        return "";
    }

    @Override
    public String getPassword(String username) {
        return "";
    }

    @Override
    public boolean updatePassword(String username, String newPassword) {
        return false;
    }
}
