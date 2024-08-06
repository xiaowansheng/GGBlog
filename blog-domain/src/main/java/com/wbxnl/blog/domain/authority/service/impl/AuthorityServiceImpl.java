package com.wbxnl.blog.domain.authority.service.impl;

import com.wbxnl.blog.common.enums.OperationCodeEnum;
import com.wbxnl.blog.common.exception.BlogException;
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
        SystemMenuEntity systemMenuEntity = authorityRepository.addSystemMenu(systemMenuVo);
        if(systemMenuEntity==null){
            throw new BlogException(OperationCodeEnum.ADD_FAILURE);
        }
        return systemMenuEntity;
    }

    @Override
    public SystemResourceEntity addSystemResource(SystemResourceVo systemResourceVo) {
        SystemResourceEntity systemResourceEntity = authorityRepository.addSystemResource(systemResourceVo);
        if(systemResourceEntity==null){
            throw new BlogException(OperationCodeEnum.ADD_FAILURE);
        }
        return systemResourceEntity;
    }

    @Override
    public void updateSystemMenu(SystemMenuUpdateEntity systemResourceUpdateEntity) {
        boolean updated = authorityRepository.updateSystemMenu(systemResourceUpdateEntity);
        if(!updated){
            throw new BlogException(OperationCodeEnum.UPDATE_FAILURE);
        }
    }

    @Override
    public void updateSystemResource(SystemResourceUpdateEntity systemResourceUpdateEntity) {
        boolean updated = authorityRepository.updateSystemResource(systemResourceUpdateEntity);
        if(!updated){
            throw new BlogException(OperationCodeEnum.UPDATE_FAILURE);
        }
    }

    @Override
    public void deleteSystemMenu(Integer id) {
        boolean updated = authorityRepository.deleteSystemMenu(id);
        if(!updated){
            throw new BlogException(OperationCodeEnum.DELETE_FAILURE);
        }
    }

    @Override
    public void deleteSystemMenu(Integer[] ids) {
        boolean updated = authorityRepository.deleteSystemMenu(ids);
        if(!updated){
            throw new BlogException(OperationCodeEnum.DELETE_FAILURE);
        }
    }

    @Override
    public void deleteSystemResource(Integer id) {
        boolean updated = authorityRepository.deleteSystemResource(id);
        if(!updated){
            throw new BlogException(OperationCodeEnum.DELETE_FAILURE);
        }
    }

    @Override
    public void deleteSystemResource(Integer[] ids) {
        boolean updated = authorityRepository.deleteSystemResource(ids);
        if(!updated){
            throw new BlogException(OperationCodeEnum.DELETE_FAILURE);
        }
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
