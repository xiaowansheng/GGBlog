package com.wbxnl.blog.dao;

import com.wbxnl.blog.model.dto.RoleMenuDto;
import com.wbxnl.blog.model.dto.RouterDto;
import com.wbxnl.blog.model.entity.RoleMenu;
import com.wbxnl.blog.dao.base.BaseDao;
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
    /**
     * 根据角色Id获取路由
     * @param roleId
     * @return
     */
    public List<RouterDto> getRoutes(@Param("roleId")Integer roleId);

//    /**
//     * 根据角色编号获取菜单列表
//     * @param roleId
//     * @return
//     */
//    List<RoleMenuDto> getRoleMenus(@Param("roleId") Integer roleId);
}
