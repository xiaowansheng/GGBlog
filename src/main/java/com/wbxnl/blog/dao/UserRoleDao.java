package com.wbxnl.blog.dao;

import com.wbxnl.blog.model.dto.UserRoleDto;
import com.wbxnl.blog.model.entity.UserRole;
import com.wbxnl.blog.dao.base.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
     * 根据用户账号获取该账号所有的角色
     * @param userAuthId
     * @return
     */
    UserRoleDto getUserRoles(@Param("userAuthId") Integer userAuthId);
}
