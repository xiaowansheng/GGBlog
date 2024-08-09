package com.wbxnl.blog.domain.authority.service;

import com.wbxnl.blog.domain.authority.model.eneity.UserMenuEntity;

import java.util.List;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/18 16:20
 */
public interface IUserAuthorityService {

    /**
     * 更新用户角色
     * @param username 用户名
     * @param roleKey 角色名
     */
    void updateUserRole(String username, String roleKey);

    /**
     * 获取用户角色菜单
     * @param username 用户名
     * @return 返回角色菜单
     */
    List<UserMenuEntity> getUserMenu(String username);

}
