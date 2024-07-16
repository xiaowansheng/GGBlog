package com.wbxnl.blog.domain.User.service;

import com.wbxnl.blog.domain.User.model.aggregate.UserBaseInfoAggregate;
import com.wbxnl.blog.domain.User.model.entity.EmailLoginEntity;
import com.wbxnl.blog.domain.User.model.entity.UserAuthEntity;
import com.wbxnl.blog.domain.User.model.entity.UserUpdateEntity;
import com.wbxnl.blog.domain.User.model.vo.UserRegisterVo;

/**
 * @description:
 * @author: xiaowansheng
 * @date: 2024/7/16 16:39
 */
public interface IUserService {
    /**
     * 用户注册
     * @param userRegisterVo 注册信息
     * @return 注册结果
     */
    boolean register(UserRegisterVo userRegisterVo);

    /**
     * 邮箱登录
     * @param emailLoginEntity 邮箱登录信息
     * @return 用户基本信息
     */
    UserBaseInfoAggregate login(EmailLoginEntity emailLoginEntity);

    /**
     * 退出登录
     * @return 退出结果
     */
    boolean logout();

    /**
     * 更新用户信息
     * @param userUpdateEntity 更新信息
     * @return 更新结果
     */
    boolean updateUserInfo(UserUpdateEntity userUpdateEntity);
}
