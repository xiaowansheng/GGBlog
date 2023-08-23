package com.wbxnl.blog.service.impl;

import com.wbxnl.blog.model.dto.UserRoleDto;
import com.wbxnl.blog.model.entity.UserRole;
import com.wbxnl.blog.dao.UserRoleDao;
import com.wbxnl.blog.service.UserRoleService;
import com.wbxnl.blog.service.impl.base.AbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户账号对应角色 服务实现类
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Service
public class UserRoleServiceImpl extends AbstractServiceImpl<UserRoleDao, UserRole,UserRoleDto, UserRole> implements UserRoleService {

    @Autowired
    public UserRoleDao userRoleDao;
    @Override
    public UserRoleDto getUserRoles(Integer userAuthId) {
        return userRoleDao.getUserRoles(userAuthId);
    }
}
