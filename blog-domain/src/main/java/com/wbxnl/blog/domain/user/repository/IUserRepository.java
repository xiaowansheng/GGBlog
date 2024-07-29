package com.wbxnl.blog.domain.user.repository;

import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import com.wbxnl.blog.domain.user.model.aggregate.UserBaseInfoAggregate;
import com.wbxnl.blog.domain.user.model.aggregate.UserDetailAggregate;
import com.wbxnl.blog.domain.user.model.aggregate.UserLoginLogAggregate;
import com.wbxnl.blog.domain.user.model.entity.*;
import com.wbxnl.blog.domain.user.model.vo.LoginLogVo;


/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/16 23:07
 */
public interface IUserRepository {

    boolean addUserAuth(UserRegisterDataEntity userRegisterDataEntity);

    boolean addUserInfo(UserRegisterInfoEntity userRegisterInfoEntity);

    boolean checkUserAvailableStatus(String username);

    UserBaseInfoAggregate getUser(String username);

    boolean logout(String username);

    boolean updateUserInfo(UserUpdateEntity userUpdateEntity);

    boolean setUserStatus(Integer id, Integer disable);

    PageData<UserDetailAggregate> getPageUserDetails(PageParams pageParams, UserQueryEntity userQueryEntity);

    boolean addLoginLog(LoginLogVo loginLogVo);

    PageData<UserLoginLogAggregate> getPageUserLoginLog(PageParams pageParams, UserLoginLogQueryEntity userLoginLogQueryEntity);

    String getVerificationCode(String email);

    String getPassword(String username);

    boolean updatePassword(String username, String newPassword);

}
