package com.wbxnl.blog.infrastructure.persistent.dao;

import com.wbxnl.blog.infrastructure.persistent.dao.base.BaseDao;
import com.wbxnl.blog.infrastructure.persistent.po.RoleResource;
import org.apache.ibatis.annotations.Mapper;

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

}
