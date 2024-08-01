package com.wbxnl.blog.infrastructure.persistent.repository;

import com.wbxnl.blog.domain.authority.model.aggregate.MenuRoleAggregate;
import com.wbxnl.blog.domain.authority.model.aggregate.ResourceRoleAggregate;
import com.wbxnl.blog.domain.authority.model.eneity.SystemMenuEntity;
import com.wbxnl.blog.domain.authority.model.eneity.SystemMenuUpdateEntity;
import com.wbxnl.blog.domain.authority.model.eneity.SystemResourceEntity;
import com.wbxnl.blog.domain.authority.model.eneity.SystemResourceUpdateEntity;
import com.wbxnl.blog.domain.authority.model.vo.SystemMenuVo;
import com.wbxnl.blog.domain.authority.model.vo.SystemResourceVo;
import com.wbxnl.blog.domain.authority.repository.IAuthorityRepository;
import com.wbxnl.blog.infrastructure.persistent.dao.RoleMenuDao;
import com.wbxnl.blog.infrastructure.persistent.dao.RoleResourceDao;
import com.wbxnl.blog.infrastructure.persistent.dao.SystemMenuDao;
import com.wbxnl.blog.infrastructure.persistent.dao.SystemResourceDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description: 略
 * TODO 暂时不需要细粒度权限控制
 *
 * @author xiaowansheng
 * @since 2024/8/1 11:37
 */
@Service
@RequiredArgsConstructor
public class AuthorityRepository implements IAuthorityRepository {

    private final SystemMenuDao systemMenuDao;

    private final SystemResourceDao systemResourceDao;

    private final RoleMenuDao roleMenuDao;

    private final RoleResourceDao roleResourceDao;

    @Override
    public SystemMenuEntity addSystemMenu(SystemMenuVo systemMenuVo) {
        return null;
    }

    @Override
    public SystemResourceEntity addSystemResource(SystemResourceVo systemResourceVo) {
        return null;
    }

    @Override
    public boolean updateSystemMenu(SystemMenuUpdateEntity systemResourceUpdateEntity) {
        return false;
    }

    @Override
    public boolean updateSystemResource(SystemResourceUpdateEntity systemResourceUpdateEntity) {
        return false;
    }

    @Override
    public boolean deleteSystemMenu(Integer id) {
        return false;
    }

    @Override
    public boolean deleteSystemMenu(Integer[] ids) {
        return false;
    }

    @Override
    public boolean deleteSystemResource(Integer id) {
        return false;
    }

    @Override
    public boolean deleteSystemResource(Integer[] ids) {
        return false;
    }

    @Override
    public List<MenuRoleAggregate> getMenuRoleList() {
        return List.of();
    }

    @Override
    public List<ResourceRoleAggregate> getResourceRoleList() {
        return List.of();
    }
}
