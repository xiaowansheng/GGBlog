package com.wbxnl.blog.domain.authority.service.impl;

import com.wbxnl.blog.domain.authority.model.aggregate.MenuRoleAggregate;
import com.wbxnl.blog.domain.authority.model.aggregate.ResourceRoleAggregate;
import com.wbxnl.blog.domain.authority.model.eneity.SystemMenuEntity;
import com.wbxnl.blog.domain.authority.model.eneity.SystemMenuUpdateEntity;
import com.wbxnl.blog.domain.authority.model.eneity.SystemResourceEntity;
import com.wbxnl.blog.domain.authority.model.eneity.SystemResourceUpdateEntity;
import com.wbxnl.blog.domain.authority.model.vo.SystemMenuVo;
import com.wbxnl.blog.domain.authority.model.vo.SystemResourceVo;
import com.wbxnl.blog.domain.authority.repository.IAuthorityRepository;
import com.wbxnl.blog.domain.authority.service.IAuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/19 9:24
 */
@Service
@RequiredArgsConstructor
public class AuthorityServiceImpl implements IAuthorityService {

    private final IAuthorityRepository authorityRepository;


    @Override
    public SystemMenuEntity addSystemMenu(SystemMenuVo systemMenuVo) {
        return authorityRepository.addSystemMenu(systemMenuVo);
    }

    @Override
    public SystemResourceEntity addSystemResource(SystemResourceVo systemResourceVo) {
        return authorityRepository.addSystemResource(systemResourceVo);
    }

    @Override
    public boolean updateSystemMenu(SystemMenuUpdateEntity systemResourceUpdateEntity) {
        return authorityRepository.updateSystemMenu(systemResourceUpdateEntity);
    }

    @Override
    public boolean updateSystemResource(SystemResourceUpdateEntity systemResourceUpdateEntity) {
        return authorityRepository.updateSystemResource(systemResourceUpdateEntity);
    }

    @Override
    public boolean deleteSystemMenu(Integer id) {
        return authorityRepository.deleteSystemMenu(id);
    }

    @Override
    public boolean deleteSystemMenu(Integer[] ids) {
        return authorityRepository.deleteSystemMenu(ids);
    }

    @Override
    public boolean deleteSystemResource(Integer id) {
        return authorityRepository.deleteSystemResource(id);
    }

    @Override
    public boolean deleteSystemResource(Integer[] ids) {
        return authorityRepository.deleteSystemResource(ids);
    }

    @Override
    public List<MenuRoleAggregate> getMenuRoleList() {
        return authorityRepository.getMenuRoleList();
    }

    @Override
    public List<ResourceRoleAggregate> getResourceRoleList() {
        return authorityRepository.getResourceRoleList();
    }
}
