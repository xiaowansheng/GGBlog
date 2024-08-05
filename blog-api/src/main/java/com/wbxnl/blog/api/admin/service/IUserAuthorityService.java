package com.wbxnl.blog.api.admin.service;

import com.wbxnl.blog.api.admin.model.res.SystemMenuInfoRes;
import com.wbxnl.blog.api.admin.model.res.SystemctlResourceInfoRes;
import com.wbxnl.blog.common.vo.PageData;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/8/5 10:12
 */
public interface IUserAuthorityService {

    /**
     * 为用户添加角色
     * @param username 用户名
     * @param roleKey  角色key
     */
    void addUserRole(String username, String roleKey);

    /**
     * 为用户删除角色
     * @param username 用户名
     * @param roleKey  角色key
     */
    void deleteUserRole(String username, String roleKey);

    /**
     * 获取当前用户菜单
     * @return 菜单列表
     */
    PageData<SystemMenuInfoRes> getSystemMenus();

    /**
     * 获取当前用户资源
     * @return 资源列表
     */
    PageData<SystemctlResourceInfoRes> getSystemResources();
}
