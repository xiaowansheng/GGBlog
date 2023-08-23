package com.wbxnl.blog.dao;

import com.wbxnl.blog.model.dto.SystemResourceDto;
import com.wbxnl.blog.model.entity.RoleResource;
import com.wbxnl.blog.dao.base.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色资源 Mapper 接口
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Mapper
public interface RoleResourceDao extends BaseDao<RoleResource> {
//    /**
//     * 获取角色对应的资源信息列表
//     * @param roleId
//     * @return
//     */
//    List<SystemResourceDto> getRoleResources(@Param("roleId") int roleId);
}
