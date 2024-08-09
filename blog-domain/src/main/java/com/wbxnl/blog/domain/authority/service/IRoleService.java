package com.wbxnl.blog.domain.authority.service;

import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import com.wbxnl.blog.domain.authority.model.eneity.*;
import com.wbxnl.blog.domain.authority.model.vo.RoleMenuVo;
import com.wbxnl.blog.domain.authority.model.vo.RoleResourceVo;
import com.wbxnl.blog.domain.authority.model.vo.RoleVo;

import java.util.List;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/18 13:43
 */
public interface IRoleService {
    /**
     * 添加角色信息
     *
     * @param roleVo 角色信息
     * @return 返回角色信息
     */
    RoleEntity addRole(RoleVo roleVo);

    /**
     * 删除角色信息
     */
    void deleteRole(Integer id);

    /**
     * 批量删除角色信息
     *
     * @param ids 角色id
     */
    void deleteRole(Integer[] ids);

    /**
     * 更新角色信息
     *
     * @param roleUpdateEntity 角色信息
     */
    void updateRole(RoleUpdateEntity roleUpdateEntity);

    /**
     * 更新角色状态
     *
     * @param id      角色id
     * @param disable 是否禁用
     */
    void updateRoleStatus(Integer id, Integer disable);

    /**
     * 获取角色信息
     * @param pageParams 分页参数
     * @param roleQueryEntity 查询条件
     * @return 角色信息
     */
    PageData<RoleEntity> getPageRole(PageParams pageParams, RoleQueryEntity roleQueryEntity);

    /**
     * 更新角色菜单
     *
     * @param roleMenuVo 角色菜单信息
     */
    void updateRoleMenu(RoleMenuVo roleMenuVo);

    /**
     * 更新角色资源
     *
     * @param roleResourceVo 角色资源信息
     */
    void updateRoleResource(RoleResourceVo roleResourceVo);

    /**
     * 获取所有菜单
     * @return 所有菜单
     */
    PageData<SystemMenuEntity> getPageMenu(PageParams pageParams, MenuQueryEntity menuQueryEntity);

    /**
     * 获取所有资源
     * @return 所有资源
     */
    PageData<SystemResourceEntity> getPageResource(PageParams pageParams, ResourceQueryEntity resourceQueryEntity);

    /**
     * 获取所有资源
     * @return 所有资源
     */
    List<SystemMenuSimpleEntity> getMenuList();

    /**
     * 获取所有资源
     * @return 所有资源
     */
    List<SystemResourceSimpleEntity> getResourceList();

    /**
     * 获取角色菜单
     * @param roleKey 角色名称
     * @return 角色菜单集合
     */
    List<String> getMenuKeyList(String roleKey);

    /**
     * 获取角色资源
     * @param roleName 角色名称
     * @return 角色资源集合
     */
    List<String> getResourceKeyList(String roleName);

}
