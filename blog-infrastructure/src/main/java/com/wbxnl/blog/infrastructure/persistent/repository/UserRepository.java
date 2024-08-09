package com.wbxnl.blog.infrastructure.persistent.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wbxnl.blog.common.cache.CacheKey;
import com.wbxnl.blog.common.utils.ObjectConvertUtils;
import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import com.wbxnl.blog.domain.user.model.aggregate.UserBaseInfoAggregate;
import com.wbxnl.blog.domain.user.model.aggregate.UserDetailAggregate;
import com.wbxnl.blog.domain.user.model.aggregate.UserLoginLogAggregate;
import com.wbxnl.blog.domain.user.model.entity.*;
import com.wbxnl.blog.domain.user.model.vo.LoginLogVo;
import com.wbxnl.blog.domain.user.repository.IUserRepository;
import com.wbxnl.blog.infrastructure.persistent.dao.*;
import com.wbxnl.blog.infrastructure.persistent.po.*;
import com.wbxnl.blog.infrastructure.persistent.redis.IRedisService;
import com.wbxnl.blog.infrastructure.persistent.utils.PageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

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

    private final UserRoleDao userRoleDao;

    private final RoleDao roleDao;

    private final LoginLogDao loginLogDao;

    private final IRedisService redisService;


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
    public boolean checkUserAvailableStatus(String username) {
        LambdaQueryWrapper<UserAuth> userAuthQueryWrapper = new LambdaQueryWrapper<UserAuth>()
                .select(UserAuth::getDisable)
                .eq(UserAuth::getUsername, username);
        UserAuth userAuth = userAuthDao.selectOne(userAuthQueryWrapper);
        boolean flag = Optional.ofNullable(userAuth).map(UserAuth::getDisable).orElse(1) == 0;
        if (!flag) {
            return false;
        }
        LambdaQueryWrapper<UserRole> userRoleQueryWrapper = new LambdaQueryWrapper<UserRole>()
                .select(UserRole::getRoleKey)
                .eq(UserRole::getUsername, username);
        UserRole userRole = userRoleDao.selectOne(userRoleQueryWrapper);
        LambdaQueryWrapper<Role> roleQueryWrapper = new LambdaQueryWrapper<Role>()
                .select(Role::getDisable)
                .eq(Role::getRoleKey, userRole.getRoleKey());
        Role role = roleDao.selectOne(roleQueryWrapper);
        return Optional.ofNullable(role).map(Role::getDisable).orElse(1) == 0;
    }

    @Override
    public UserBaseInfoAggregate getUser(String username) {
        // 获取用户账户
        LambdaQueryWrapper<UserAuth> userAuthQueryWrapper = new LambdaQueryWrapper<UserAuth>()
                .eq(UserAuth::getUsername, username);
        UserAuth userAuth = userAuthDao.selectOne(userAuthQueryWrapper);
        // 获取用户信息
        LambdaQueryWrapper<UserInfo> userInfoQueryWrapper = new LambdaQueryWrapper<UserInfo>()
                .eq(UserInfo::getUserInfoKey, userAuth.getUserInfoKey());
        UserInfo userInfo = userInfoDao.selectOne(userInfoQueryWrapper);
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
    public boolean logout(String username) {
        redisService.del(CacheKey.getLoginInfoKey(username));
        return true;
    }

    @Override
    public boolean updateUserInfo(UserUpdateEntity userUpdateEntity) {
        UserInfo userInfo = ObjectConvertUtils.convert(userUpdateEntity, UserInfo.class);
        return userInfoDao.update(userInfo, new LambdaQueryWrapper<UserInfo>()
                .eq(UserInfo::getUserInfoKey, userUpdateEntity.getUserInfoKey())) > 0;
    }

    @Override
    public boolean setUserStatus(Integer id, Integer disable) {
        LambdaUpdateWrapper<UserAuth> userAuthLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        userAuthLambdaUpdateWrapper.set(UserAuth::getDisable, disable)
                .eq(UserAuth::getId, id);
        return userAuthDao.update(userAuthLambdaUpdateWrapper) > 0;
    }

    @Override
    public PageData<UserDetailAggregate> getPageOfUserDetails(PageParams pageParams, UserQueryEntity userQueryEntity) {
        List<UserDetailAggregate> userDetailAggregates = userAuthDao.getPageOfUserDetails(pageParams, userQueryEntity);
        Long total = userAuthDao.getPageUserDetailsTotal(userQueryEntity);
        return PageUtils.convertPageData(pageParams.getNumber(), pageParams.getSize(), total, userDetailAggregates);
    }

    @Override
    public boolean addLoginLog(LoginLogVo loginLogVo) {
        LoginLog loginLog = ObjectConvertUtils.convert(loginLogVo, LoginLog.class);
        return loginLogDao.insert(loginLog) > 0;
    }

    @Override
    public PageData<UserLoginLogAggregate> getPageOfUserLoginLog(PageParams pageParams, UserLoginLogQueryEntity userLoginLogQueryEntity) {
        LambdaQueryWrapper<LoginLog> loginLogQueryWrapper = new LambdaQueryWrapper<>();
        Page<LoginLog> loginLogPage = new Page<>(pageParams.getNumber(), pageParams.getSize());
        LambdaQueryWrapper<LoginLog> queryWrapper = loginLogQueryWrapper
                .like(StringUtils.isNotBlank(userLoginLogQueryEntity.getUsername()), LoginLog::getUsername, userLoginLogQueryEntity.getUsername())
                .like(StringUtils.isNotBlank(userLoginLogQueryEntity.getIpAddress()), LoginLog::getIpAddress, userLoginLogQueryEntity.getIpAddress())
                .like(StringUtils.isNotBlank(userLoginLogQueryEntity.getIpSource()), LoginLog::getIpSource, userLoginLogQueryEntity.getIpSource())
                .like(StringUtils.isNotBlank(userLoginLogQueryEntity.getDevice()), LoginLog::getDevice, userLoginLogQueryEntity.getDevice())
                .like(StringUtils.isNotBlank(userLoginLogQueryEntity.getBrowser()), LoginLog::getBrowser, userLoginLogQueryEntity.getBrowser())
                .like(StringUtils.isNotBlank(userLoginLogQueryEntity.getLocation()), LoginLog::getLocation, userLoginLogQueryEntity.getLocation())
                .between(!(ObjectUtils.isEmpty(userLoginLogQueryEntity.getBeginCreateTime()) && ObjectUtils.isEmpty(userLoginLogQueryEntity.getEndCreateTime())), LoginLog::getCreateTime, userLoginLogQueryEntity.getBeginCreateTime(), userLoginLogQueryEntity.getEndCreateTime())
                .orderByDesc(LoginLog::getCreateTime);
        Page<LoginLog> selectedPage = loginLogDao.selectPage(loginLogPage, queryWrapper);
        List<UserLoginLogAggregate> userLoginLogAggregates = ObjectConvertUtils.convertList(selectedPage.getRecords(), UserLoginLogAggregate.class);
        return PageUtils.convertPageData((int) selectedPage.getCurrent(), (int) selectedPage.getSize(), selectedPage.getTotal(), userLoginLogAggregates);
    }

    @Override
    public String getVerificationCode(String email) {
        return redisService.getString(CacheKey.getVerificationKey(email));
    }

    @Override
    public String getPassword(String username) {
        LambdaQueryWrapper<UserAuth> userAuthQueryWrapper = new LambdaQueryWrapper<>();
        userAuthQueryWrapper.select(UserAuth::getPassword).eq(UserAuth::getUsername, username);
        UserAuth userAuth = userAuthDao.selectOne(userAuthQueryWrapper);
        return Optional.ofNullable(userAuth).map(UserAuth::getPassword).orElse(null);
    }

    @Override
    public boolean updatePassword(String username, String newPassword) {
        LambdaUpdateWrapper<UserAuth> userAuthLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        userAuthLambdaUpdateWrapper.set(UserAuth::getPassword, newPassword)
                .eq(UserAuth::getUsername, username);
        return userAuthDao.update(userAuthLambdaUpdateWrapper) > 0;
    }

    @Override
    public PageData<UserLoginLogAggregate> getPageUserLoginsByUsername(PageParams pageParams, String username) {
        LambdaQueryWrapper<LoginLog> loginLogQueryWrapper = new LambdaQueryWrapper<>();
        Page<LoginLog> loginLogPage = new Page<>(pageParams.getNumber(), pageParams.getSize());
        LambdaQueryWrapper<LoginLog> queryWrapper = loginLogQueryWrapper
                .eq(LoginLog::getUsername, username)
                .orderByDesc(LoginLog::getCreateTime);
        Page<LoginLog> selectedPage = loginLogDao.selectPage(loginLogPage, queryWrapper);
        List<UserLoginLogAggregate> userLoginLogAggregates = ObjectConvertUtils.convertList(selectedPage.getRecords(), UserLoginLogAggregate.class);
        return PageUtils.convertPageData((int) selectedPage.getCurrent(), (int) selectedPage.getSize(), selectedPage.getTotal(), userLoginLogAggregates);
    }
}
