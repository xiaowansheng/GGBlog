package com.wbxnl.blog.infrastructure.persistent.dao;

import com.wbxnl.blog.infrastructure.persistent.dao.base.BaseDao;
import com.wbxnl.blog.infrastructure.persistent.po.RoleMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色菜单 Mapper 接口
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Mapper
public interface RoleMenuDao extends BaseDao<RoleMenu> {
//    /**
//     * 根据角色Id获取路由
//     * @param roleId
//     * @return
//     */
//    public List<RouterDto> getRoutes(@Param("roleId")Integer roleId);

}
