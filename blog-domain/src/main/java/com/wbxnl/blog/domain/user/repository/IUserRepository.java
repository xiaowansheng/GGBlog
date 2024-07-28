package com.wbxnl.blog.domain.user.repository;

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


/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/16 23:07
 */
public interface IUserRepository {

    boolean register(UserRegisterVo userRegisterVo);

    UserBaseInfoAggregate getUser(EmailLoginEntity emailLoginEntity);

    boolean logout();

    boolean updateUserInfo(UserUpdateEntity userUpdateEntity);

    boolean setUserStatus(Integer id, Integer disable);

    PageData<UserDetailAggregate> getPageUserDetails(PageParams pageParams, UserQueryEntity userQueryEntity);

    boolean addLoginLog(LoginLogVo loginLogVo);

    PageData<UserLoginLogAggregate> getPageUserLogins(PageParams pageParams, UserLoginLogQueryEntity userLoginLogQueryEntity);

    String getVerificationCode(String email);

    String getPassword(String username);

    boolean updatePassword(String username, String newPassword);
}
