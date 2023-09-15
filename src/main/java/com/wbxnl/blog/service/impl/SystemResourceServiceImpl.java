package com.wbxnl.blog.service.impl;

import com.wbxnl.blog.filter.AuthorizationManagerImpl;
import com.wbxnl.blog.model.dto.ResourceRoleDto;
import com.wbxnl.blog.model.dto.SystemResourceDto;
import com.wbxnl.blog.model.entity.RoleResource;
import com.wbxnl.blog.model.entity.SystemResource;
import com.wbxnl.blog.dao.SystemResourceDao;
import com.wbxnl.blog.model.vo.SystemResourceVo;
import com.wbxnl.blog.service.RoleResourceService;
import com.wbxnl.blog.service.SystemResourceService;
import com.wbxnl.blog.service.impl.base.AbstractServiceImpl;
import com.wbxnl.blog.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 资源菜单 服务实现类
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Service
public class SystemResourceServiceImpl extends AbstractServiceImpl<SystemResourceDao, SystemResource, SystemResourceDto, SystemResourceVo> implements SystemResourceService {
    @Autowired
    private SystemResourceDao systemResourceDao;

    @Autowired
    private RoleResourceService roleResourceService;


    private AuthorizationManagerImpl authorizationManager;

    @Autowired
    public void setAuthorizationManager(@Lazy AuthorizationManagerImpl authorizationManager) {
        this.authorizationManager = authorizationManager;
    }

    @Override
    public List<SystemResourceDto> getResourceTree() {
        List<SystemResource> resourceList = list();
        List<SystemResourceDto> resourceDtoList = ConvertUtils.sourceToTarget(resourceList, SystemResourceDto.class);
        List<SystemResourceDto> menuTree= getResourceTree(0,resourceDtoList);
        return menuTree;
    }

    @Override
    public List<SystemResourceDto> getResourceSimpleTree() {
        List<SystemResource> resourceList = lambdaQuery()
                .select(SystemResource::getId,SystemResource::getName,SystemResource::getParentId)
                .list();
        List<SystemResourceDto> resourceDtoList = ConvertUtils.sourceToTarget(resourceList, SystemResourceDto.class);
        List<SystemResourceDto> menuTree= getResourceTree(0,resourceDtoList);
        return menuTree;
    }

    @Override
    public List<ResourceRoleDto> getResourceRoles() {
        return systemResourceDao.getResourceRoles();
    }


    /**
     * 递归遍历使链表形成树形结构
     * @param rootIndex 父节点ID，根节点默认为0
     * @param resourceDtoList
     * @return
     */
    private List<SystemResourceDto> getResourceTree(int rootIndex, List<SystemResourceDto> resourceDtoList) {
        // TODO 或许可以优化，以后再看
        List<SystemResourceDto> root=new ArrayList<>();
        for (SystemResourceDto resourceDto:resourceDtoList) {
            if(resourceDto.getParentId()==rootIndex){
                root.add(resourceDto);
//                menuDtoList.remove(menuDto);
                List<SystemResourceDto> menuTree = getResourceTree(resourceDto.getId(), resourceDtoList);
                resourceDto.setChildren(menuTree);
            }
        }
        return root;
    }


    @Override
    public SystemResource saveVo(SystemResourceVo vo) {
        SystemResource systemResource = ConvertUtils.sourceToTarget(vo, SystemResource.class);
        // 保存新的资源信息
        save(systemResource);
        //保存新的资源信息后，清楚权限控制里的资源列表，等待下一次权限校验时重新加载系统接口权限表，以便能获取最新的资源数据
        authorizationManager.clearDataSource();
        return systemResource;
    }

    @Override
    public void update(SystemResourceVo vo) {
        super.update(vo);
        //修改新的接口信息后，清楚权限控制里的资源列表，等待新的权限请求处理，再重新加载系统接口权限表
        authorizationManager.clearDataSource();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteById(Integer id) {
        //删除角色接口
        roleResourceService.lambdaUpdate().eq(RoleResource::getResourceId,id).remove();
        //删除接口
        removeById(id);
        // 删除资源接口后，清除权限控制的资源列表数据，等待下一次权限控制时访问，重新加载最新的数据
        authorizationManager.clearDataSource();
    }
}
