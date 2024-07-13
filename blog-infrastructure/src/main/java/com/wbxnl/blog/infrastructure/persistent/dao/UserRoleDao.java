package com.wbxnl.blog.infrastructure.persistent.dao;

import com.wbxnl.blog.infrastructure.persistent.dao.base.BaseDao;
import com.wbxnl.blog.infrastructure.persistent.po.UserRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户账号对应角色 Mapper 接口
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Mapper
public interface UserRoleDao extends BaseDao<UserRole> {
    /**
//     * 根据用户账号获取该账号所有的角色
//     * @param userAuthId
//     * @return
//     */
//    UserRoleDto getUserRoles(@Param("userAuthId") Integer userAuthId);
}
