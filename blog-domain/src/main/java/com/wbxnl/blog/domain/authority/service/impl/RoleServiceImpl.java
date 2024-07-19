package com.wbxnl.blog.domain.authority.service.impl;

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
    public boolean deleteRole(Integer id) {
        return roleRepository.deleteRole(id);
    }

    @Override
    public boolean deleteRole(Integer[] ids) {
        return roleRepository.deleteRole(ids);
    }

    @Override
    public boolean updateRole(RoleVo roleVo) {
        return roleRepository.updateRole(roleVo);
    }

    @Override
    public boolean updateRoleStatus(Integer id, Integer disable) {
        return roleRepository.updateRoleStatus(id, disable);
    }

    @Override
    public PageData<RoleEntity> getPageRole(PageParams pageParams, RoleQueryEntity roleQueryEntity) {
        return roleRepository.getPageRole(pageParams, roleQueryEntity);
    }

    @Override
    public boolean updateRoleMenu(RoleMenuVo roleMenuVo) {
        return roleRepository.updateRoleMenu(roleMenuVo);
    }

    @Override
    public boolean updateRoleResource(RoleResourceVo roleResourceVo) {
        return roleRepository.updateRoleResource(roleResourceVo);
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
    public List<String> getMenuNameList(String roleName) {
        return roleRepository.getMenuNameList(roleName);
    }

    @Override
    public List<String> getResourceNameList(String roleName) {
        return roleRepository.getResourceNameList(roleName);
    }
}
