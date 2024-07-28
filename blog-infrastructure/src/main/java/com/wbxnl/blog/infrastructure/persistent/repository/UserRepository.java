package com.wbxnl.blog.infrastructure.persistent.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wbxnl.blog.common.utils.ObjectConvertUtils;
import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import com.wbxnl.blog.domain.user.model.aggregate.UserBaseInfoAggregate;
import com.wbxnl.blog.domain.user.model.aggregate.UserDetailAggregate;
import com.wbxnl.blog.domain.user.model.aggregate.UserLoginLogAggregate;
import com.wbxnl.blog.domain.user.model.entity.*;
import com.wbxnl.blog.domain.user.model.vo.LoginLogVo;
import com.wbxnl.blog.domain.user.model.vo.UserRegisterVo;
import com.wbxnl.blog.domain.user.repository.IUserRepository;
import com.wbxnl.blog.infrastructure.persistent.dao.UserAuthDao;
import com.wbxnl.blog.infrastructure.persistent.dao.UserInfoDao;
import com.wbxnl.blog.infrastructure.persistent.po.UserAuth;
import com.wbxnl.blog.infrastructure.persistent.po.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/28 20:33
 */
@Service
@RequiredArgsConstructor
public class UserRepository implements IUserRepository {

    private final UserAuthDao userAuthDao;

    private final UserInfoDao userInfoDao;


    @Override
    public boolean addUserAuth(UserRegisterDataEntity userRegisterDataEntity) {
        UserAuth userAuth = ObjectConvertUtils.convert(userRegisterDataEntity, UserAuth.class);
        userAuth.setDisable(1);
        return userAuthDao.insert(userAuth) > 0;
    }

    @Override
    public boolean addUserInfo(UserRegisterInfoEntity userRegisterInfoEntity) {
        UserInfo userInfo = ObjectConvertUtils.convert(userRegisterInfoEntity, UserInfo.class);
        userInfo.setEmail(userRegisterInfoEntity.getEmail());
        return userInfoDao.insert(userInfo) > 0;
    }

    @Override
    public UserBaseInfoAggregate getUser(String username) {
        // 获取用户账户
        QueryWrapper<UserAuth> authQueryWrapper = new QueryWrapper<>();
        authQueryWrapper.eq("username", username);
        UserAuth userAuth = userAuthDao.selectOne(authQueryWrapper);
        // 获取用户信息
        QueryWrapper<UserInfo> infoQueryWrapper = new QueryWrapper<>();
        infoQueryWrapper.eq("user_info_key", userAuth.getUserInfoKey());
        UserInfo userInfo = userInfoDao.selectOne(infoQueryWrapper);
        // 封装信息
        UserBaseInfoAggregate userBaseInfoAggregate = new UserBaseInfoAggregate();
        userBaseInfoAggregate.setUsername(userAuth.getUsername());
        userBaseInfoAggregate.setAvatar(userInfo.getAvatar());
        userBaseInfoAggregate.setEmail(userInfo.getEmail());
        userBaseInfoAggregate.setIntroduction(userInfo.getIntroduction());
        userBaseInfoAggregate.setNickname(userInfo.getNickname());
        userBaseInfoAggregate.setQq(userInfo.getQq());
        userBaseInfoAggregate.setSignature(userInfo.getSignature());
        userBaseInfoAggregate.setWebsite(userInfo.getWebsite());
        userBaseInfoAggregate.setLoginType(userAuth.getLoginType());
        userBaseInfoAggregate.setId(userAuth.getId());
        userBaseInfoAggregate.setCreateTime(userAuth.getCreateTime());
        userBaseInfoAggregate.setLoginType(userAuth.getLoginType());
        return userBaseInfoAggregate;
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
