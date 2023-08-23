package com.wbxnl.blog.service;

import com.wbxnl.blog.model.dto.UserRoleDto;
import com.wbxnl.blog.model.entity.UserRole;
import com.wbxnl.blog.service.base.BaseService;


/**
 * <p>
 * 用户账号对应角色 服务类
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
public interface UserRoleService extends BaseService< UserRole,UserRoleDto, UserRole> {
    /**
     * 根据账号查询对应角色集合
     * @param userAuthId
     * @return
     */
    UserRoleDto getUserRoles(Integer userAuthId);

}
