package com.wbxnl.blog.infrastructure.persistent.repository;

import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import com.wbxnl.blog.domain.authority.model.eneity.*;
import com.wbxnl.blog.domain.authority.model.vo.RoleMenuVo;
import com.wbxnl.blog.domain.authority.model.vo.RoleResourceVo;
import com.wbxnl.blog.domain.authority.model.vo.RoleVo;
import com.wbxnl.blog.domain.authority.repository.IRoleRepository;
import com.wbxnl.blog.infrastructure.persistent.dao.RoleDao;
import com.wbxnl.blog.infrastructure.persistent.dao.RoleMenuDao;
import com.wbxnl.blog.infrastructure.persistent.dao.RoleResourceDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description: 略
 * TODO 暂时不需要细粒度权限控制
 *
 * @author xiaowansheng
 * @since 2024/8/1 9:45
 */
@Service
@RequiredArgsConstructor
public class RoleRepository implements IRoleRepository {

    private final RoleDao roleDao;

    private final RoleMenuDao roleMenuDao;

    private final RoleResourceDao roleResourceDao;

    @Override
    public RoleEntity addRole(RoleVo roleVo) {
        return null;
    }

    @Override
    public boolean deleteRole(Integer id) {
        return false;
    }

    @Override
    public boolean deleteRole(Integer[] ids) {
        return false;
    }

    @Override
    public boolean updateRole(RoleUpdateEntity roleUpdateEntity) {
        return false;
    }

    @Override
    public boolean updateRoleStatus(Integer id, Integer disable) {
        return false;
    }

    @Override
    public PageData<RoleEntity> getPageRole(PageParams pageParams, RoleQueryEntity roleQueryEntity) {
        return null;
    }

    @Override
    public boolean updateRoleMenu(RoleMenuVo roleMenuVo) {
        return false;
    }

    @Override
    public boolean updateRoleResource(RoleResourceVo roleResourceVo) {
        return false;
    }

    @Override
    public PageData<SystemMenuEntity> getPageMenu(PageParams pageParams, MenuQueryEntity menuQueryEntity) {
        return null;
    }

    @Override
    public PageData<SystemResourceEntity> getPageResource(PageParams pageParams, ResourceQueryEntity resourceQueryEntity) {
        return null;
    }

    @Override
    public List<SystemMenuSimpleEntity> getMenuList() {
        return List.of();
    }

    @Override
    public List<SystemResourceSimpleEntity> getResourceList() {
        return List.of();
    }

    @Override
    public List<String> getMenuKeyList(String roleKey) {
        return List.of();
    }

    @Override
    public List<String> getResourceKeyList(String roleKey) {
        return List.of();
    }
}
