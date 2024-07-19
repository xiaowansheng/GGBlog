package com.wbxnl.blog.domain.authority.repository;

import com.wbxnl.blog.domain.authority.model.aggregate.RoleMenuAggregate;
import com.wbxnl.blog.domain.authority.model.aggregate.RoleResourceAggregate;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/19 10:23
 */
public interface IUserAuthorityRepository {

    boolean updateUserRole(Integer username, Integer roleName);

    RoleMenuAggregate getUserRoleMenu(Integer username);

    RoleResourceAggregate getUserRoleResource(Integer username);

}
