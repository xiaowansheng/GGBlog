package com.wbxnl.blog.domain.authority.repository;

import com.wbxnl.blog.domain.authority.model.eneity.UserMenuEntity;

import java.util.List;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/19 10:23
 */
public interface IUserAuthorityRepository {

    boolean updateUserRole(String username, String roleKey);

    List<UserMenuEntity> getUserRoleMenu(String username);

}
