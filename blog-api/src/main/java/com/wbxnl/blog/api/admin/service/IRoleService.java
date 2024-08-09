package com.wbxnl.blog.api.admin.service;

import com.wbxnl.blog.api.admin.model.req.RoleDataReq;
import com.wbxnl.blog.api.admin.model.res.SystemMenuSimpleRes;
import com.wbxnl.blog.api.admin.model.res.SystemResourceSimpleRes;
import com.wbxnl.blog.common.vo.KeyData;

import java.util.List;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/8/4 19:03
 */
public interface IRoleService {

    /**
     * 添加角色
     *
     * @param roleDataReq 角色数据
     * @return 角色数据
     */
    KeyData addRole(RoleDataReq roleDataReq);

    /**
     * 删除角色
     *
     * @param id 角色id
     */
    void deleteRole(Integer id);

    /**
     * 删除角色
     *
     * @param ids 角色id
     */
    void deleteRole(Integer[] ids);

    /**
     * 更新角色
     *
     * @param roleDataReq 角色数据
     */
    void updateRole(RoleDataReq roleDataReq);

    /**
     * 为用户添加角色
     *
     * @param username 用户id
     * @param roleKey  角色key
     */
    void addUserRole(String username, String roleKey);

    /**
     * 为用户删除角色
     *
     * @param username 用户id
     * @param roleKey  角色key
     */
    void deleteUserRole(String username, String roleKey);

    /**
     * 为角色添加菜单
     * @param roleKey 角色key
     * @param menuKeys 菜单key
     */
    void addRoleMenu(String roleKey, List<String> menuKeys);

    /**
     * 获取角色菜单
     * @param roleKey 角色key
     * @return 菜单列表
     */
    List<String> getSystemMenus(String roleKey);

    /**
     * 为角色添加资源
     * @param roleKey 角色key
     * @param resourceKeys 资源key
     */
    void addRoleResource(String roleKey, List<String> resourceKeys);

    /**
     * 获取角色资源
     * @param roleKey 角色key
     * @return 资源列表
     */
    List<String> getSystemResources(String roleKey);
}
