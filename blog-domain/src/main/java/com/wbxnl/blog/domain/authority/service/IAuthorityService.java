package com.wbxnl.blog.domain.authority.service;

import com.wbxnl.blog.domain.authority.model.aggregate.MenuRoleAggregate;
import com.wbxnl.blog.domain.authority.model.aggregate.ResourceRoleAggregate;

import java.util.List;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/18 17:51
 */
public interface IAuthorityService {
    /**
     * 获取所有菜单的角色列表信息
     * @return 返回菜单角色列表
     */
    List<MenuRoleAggregate> getMenuRoleList();

    /**
     * 获取所有资源的角色列表信息
     * @return 返回资源角色列表
     */
    List<ResourceRoleAggregate> getResourceRoleList();
}
