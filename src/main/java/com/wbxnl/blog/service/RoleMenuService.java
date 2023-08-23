package com.wbxnl.blog.service;

import com.wbxnl.blog.model.dto.RoleMenuDto;
import com.wbxnl.blog.model.dto.RouterDto;
import com.wbxnl.blog.model.entity.RoleMenu;
import com.wbxnl.blog.model.vo.RoleMenuVo;
import com.wbxnl.blog.service.base.BaseService;

import java.util.List;

/**
 * <p>
 * 角色菜单 服务类
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
public interface RoleMenuService extends BaseService<RoleMenu, RoleMenuDto, RoleMenuVo> {
    /**
     * 获取角色路由菜单
     * @return
     */
    public List<RouterDto> getRoutes();

//    /**
//     * 获取角色菜单列表
//     *
//     * @param roleId
//     * @return
//     */
//    List<RoleMenuDto> getRoleMenus(Integer roleId);
}
