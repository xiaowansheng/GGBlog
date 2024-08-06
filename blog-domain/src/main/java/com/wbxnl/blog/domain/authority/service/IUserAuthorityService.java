package com.wbxnl.blog.domain.authority.service;

import com.wbxnl.blog.domain.authority.model.aggregate.RoleMenuAggregate;
import com.wbxnl.blog.domain.authority.model.aggregate.RoleResourceAggregate;

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
     * @param roleName 角色名
     */
    void updateUserRole(Integer username, Integer roleName);

    /**
     * 获取用户角色菜单
     * @param username 用户名
     * @return 返回角色菜单
     */
    RoleMenuAggregate getUserRoleMenu(Integer username);

    /**
     * 获取用户角色资源
     * @param username 用户名
     * @return 返回角色资源
     */
    RoleResourceAggregate getUserRoleResource(Integer username);

}
