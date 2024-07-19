package com.wbxnl.blog.domain.authority.service.impl;

import com.wbxnl.blog.domain.authority.model.aggregate.RoleMenuAggregate;
import com.wbxnl.blog.domain.authority.model.aggregate.RoleResourceAggregate;
import com.wbxnl.blog.domain.authority.repository.IUserAuthorityRepository;
import com.wbxnl.blog.domain.authority.service.IUserAuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/19 10:22
 */
@Service
@RequiredArgsConstructor
public class UserAuthorityService implements IUserAuthorityService {

    private final IUserAuthorityRepository userAuthorityRepository;

    @Override
    public boolean updateUserRole(Integer username, Integer roleName) {
        return userAuthorityRepository.updateUserRole(username, roleName);
    }

    @Override
    public RoleMenuAggregate getUserRoleMenu(Integer username) {
        return userAuthorityRepository.getUserRoleMenu(username);
    }

    @Override
    public RoleResourceAggregate getUserRoleResource(Integer username) {
        return userAuthorityRepository.getUserRoleResource(username);
    }
}
