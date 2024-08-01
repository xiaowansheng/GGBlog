package com.wbxnl.blog.infrastructure.persistent.repository;

import com.wbxnl.blog.domain.authority.model.aggregate.RoleMenuAggregate;
import com.wbxnl.blog.domain.authority.model.aggregate.RoleResourceAggregate;
import com.wbxnl.blog.domain.authority.repository.IUserAuthorityRepository;
import com.wbxnl.blog.infrastructure.persistent.dao.UserRoleDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * description: 略
 * TODO 暂时不需要细粒度权限控制
 *
 * @author xiaowansheng
 * @since 2024/8/1 11:39
 */
@Service
@RequiredArgsConstructor
public class UserAuthorityRepository implements IUserAuthorityRepository {

    private final UserRoleDao userRoleDao;

    @Override
    public boolean updateUserRole(Integer username, Integer roleName) {
        return false;
    }

    @Override
    public RoleMenuAggregate getUserRoleMenu(Integer username) {
        return null;
    }

    @Override
    public RoleResourceAggregate getUserRoleResource(Integer username) {
        return null;
    }
}
