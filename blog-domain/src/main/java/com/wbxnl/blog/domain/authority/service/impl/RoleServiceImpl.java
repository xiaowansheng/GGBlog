package com.wbxnl.blog.domain.authority.service.impl;

import com.wbxnl.blog.common.enums.OperationCodeEnum;
import com.wbxnl.blog.common.exception.BlogException;
import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import com.wbxnl.blog.domain.authority.model.eneity.*;
import com.wbxnl.blog.domain.authority.model.vo.RoleMenuVo;
import com.wbxnl.blog.domain.authority.model.vo.RoleResourceVo;
import com.wbxnl.blog.domain.authority.model.vo.RoleVo;
import com.wbxnl.blog.domain.authority.repository.IRoleRepository;
import com.wbxnl.blog.domain.authority.service.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/19 10:15
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements IRoleService {

    private final IRoleRepository roleRepository;

    @Override
    public RoleEntity addRole(RoleVo roleVo) {
        return roleRepository.addRole(roleVo);
    }

    @Override
    public void deleteRole(Integer id) {
        boolean updated = roleRepository.deleteRole(id);
        if(!updated){
            throw new BlogException(OperationCodeEnum.DELETE_FAILURE);
        }
    }

    @Override
    public void deleteRole(Integer[] ids) {
        boolean updated = roleRepository.deleteRole(ids);
        if(!updated){
            throw new BlogException(OperationCodeEnum.DELETE_FAILURE);
        }
    }

    @Override
    public void updateRole(RoleUpdateEntity roleUpdateEntity) {
        boolean updated = roleRepository.updateRole(roleUpdateEntity);
        if(!updated){
            throw new BlogException(OperationCodeEnum.UPDATE_FAILURE);
        }
    }

    @Override
    public void updateRoleStatus(Integer id, Integer disable) {
        boolean updated = roleRepository.updateRoleStatus(id, disable);
        if(!updated){
            throw new BlogException(OperationCodeEnum.UPDATE_FAILURE);
        }
    }

    @Override
    public PageData<RoleEntity> getPageRole(PageParams pageParams, RoleQueryEntity roleQueryEntity) {
        return roleRepository.getPageRole(pageParams, roleQueryEntity);
    }

    @Override
    public void updateRoleMenu(RoleMenuVo roleMenuVo) {
        boolean updated = roleRepository.updateRoleMenu(roleMenuVo);
        if(!updated){
            throw new BlogException(OperationCodeEnum.UPDATE_FAILURE);
        }
    }

    @Override
    public void updateRoleResource(RoleResourceVo roleResourceVo) {
        boolean updated = roleRepository.updateRoleResource(roleResourceVo);
        if(!updated){
            throw new BlogException(OperationCodeEnum.UPDATE_FAILURE);
        }
    }

    @Override
    public PageData<SystemMenuEntity> getPageMenu(PageParams pageParams, MenuQueryEntity menuQueryEntity) {
        return roleRepository.getPageMenu(pageParams, menuQueryEntity);
    }

    @Override
    public PageData<SystemResourceEntity> getPageResource(PageParams pageParams, ResourceQueryEntity resourceQueryEntity) {
        return roleRepository.getPageResource(pageParams, resourceQueryEntity);
    }

    @Override
    public List<SystemMenuSimpleEntity> getMenuList() {
        return roleRepository.getMenuList();
    }

    @Override
    public List<SystemResourceSimpleEntity> getResourceList() {
        return roleRepository.getResourceList();
    }

    @Override
    public List<String> getMenuNameList(String roleKey) {
        return roleRepository.getMenuNameList(roleKey);
    }

    @Override
    public List<String> getResourceNameList(String roleName) {
        return roleRepository.getResourceNameList(roleName);
    }
}
