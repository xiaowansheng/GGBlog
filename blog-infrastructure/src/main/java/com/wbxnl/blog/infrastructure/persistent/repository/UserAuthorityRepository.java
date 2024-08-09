package com.wbxnl.blog.infrastructure.persistent.repository;

import com.wbxnl.blog.domain.authority.model.eneity.UserMenuEntity;
import com.wbxnl.blog.domain.authority.repository.IUserAuthorityRepository;
import com.wbxnl.blog.infrastructure.persistent.dao.UserRoleDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public boolean updateUserRole(String username, String roleKey) {
        return false;
    }

    @Override
    public List<UserMenuEntity> getUserRoleMenu(String username) {
        return null;
    }
}
