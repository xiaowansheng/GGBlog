package com.wbxnl.blog.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.wbxnl.blog.filter.AuthorizationManagerImpl;
import com.wbxnl.blog.model.dto.RoleResourceDto;
import com.wbxnl.blog.model.entity.RoleResource;
import com.wbxnl.blog.dao.RoleResourceDao;
import com.wbxnl.blog.model.vo.RoleResourceVo;
import com.wbxnl.blog.service.RoleResourceService;
import com.wbxnl.blog.service.impl.base.AbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 角色资源 服务实现类
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Service
public class RoleResourceServiceImpl extends AbstractServiceImpl<RoleResourceDao, RoleResource, RoleResourceDto, RoleResourceVo> implements RoleResourceService {

    @Autowired
    RoleResourceDao roleResourceDao;


    private AuthorizationManagerImpl authorizationManager;

    @Autowired
    public void setAuthorizationManager(@Lazy AuthorizationManagerImpl authorizationManager) {
        this.authorizationManager = authorizationManager;
    }
    //    @Override
//    public List<SystemResourceDto> getRoleResources(int roleId){
//        return roleResourceDao.getRoleResources(roleId);
//    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateBatch(List<RoleResourceVo> roleResourceVos) {
        if(CollectionUtils.isEmpty(roleResourceVos)){
            return;
        }
        // 删除所以该角色相关的资源编号权限
        lambdaUpdate().eq(RoleResource::getRoleId,roleResourceVos.get(0).getRoleId()).remove();
        // 重新添加新的角色资源信息
        super.saveVoBatch(roleResourceVos);
        // 修改用户接口权限后，重新加载系统权限信息
        authorizationManager.clearDataSource();
    }
}
