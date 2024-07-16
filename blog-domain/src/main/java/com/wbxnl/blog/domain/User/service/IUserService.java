package com.wbxnl.blog.domain.user.service;

import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import com.wbxnl.blog.domain.user.model.aggregate.UserBaseInfoAggregate;
import com.wbxnl.blog.domain.user.model.entity.EmailLoginEntity;
import com.wbxnl.blog.domain.user.model.entity.UserQueryEntity;
import com.wbxnl.blog.domain.user.model.entity.UserUpdateEntity;
import com.wbxnl.blog.domain.user.model.vo.UserRegisterVo;
import com.wbxnl.blog.domain.user.model.aggregate.UserDetailAggregate;


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

    /**
     * 禁用用户
     * @param id 用户id
     * @param disable 禁用状态
     * @return 结果
     */
    boolean setUserStatus(Integer id, Integer disable);

    /**
     * 获取用户信息
     * @param pageParams 分页信息
     * @param userQueryEntity 查询信息
     * @return 用户信息
     */
    PageData<UserDetailAggregate> getPageUserDetails(PageParams pageParams, UserQueryEntity userQueryEntity);

}
