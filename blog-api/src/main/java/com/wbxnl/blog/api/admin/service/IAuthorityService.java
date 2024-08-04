package com.wbxnl.blog.api.admin.service;

import com.wbxnl.blog.api.admin.model.req.RoleDataReq;
import com.wbxnl.blog.common.vo.KeyData;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/8/4 19:03
 */
public interface IAuthorityService {

    /**
     * 添加角色
     *
     * @param roleDataReq 角色数据
     * @return 角色数据
     */
    KeyData addRole(RoleDataReq roleDataReq);

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
}
