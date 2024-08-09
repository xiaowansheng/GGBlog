package com.wbxnl.blog.domain.authority.service.impl;

import com.wbxnl.blog.common.enums.OperationCodeEnum;
import com.wbxnl.blog.common.exception.BlogException;
import com.wbxnl.blog.domain.authority.model.eneity.UserMenuEntity;
import com.wbxnl.blog.domain.authority.repository.IUserAuthorityRepository;
import com.wbxnl.blog.domain.authority.service.IUserAuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void updateUserRole(String username, String roleKey) {
        boolean updated = userAuthorityRepository.updateUserRole(username, roleKey);
        if(!updated){
            throw new BlogException(OperationCodeEnum.UPDATE_FAILURE);
        }
    }

    @Override
    public List<UserMenuEntity> getUserMenu(String username) {
        return userAuthorityRepository.getUserRoleMenu(username);
    }
}
