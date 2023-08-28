package com.wbxnl.blog.service;

import com.wbxnl.blog.model.dto.ResourceRoleDto;
import com.wbxnl.blog.model.dto.SystemResourceDto;
import com.wbxnl.blog.model.entity.SystemResource;
import com.wbxnl.blog.model.vo.SystemResourceVo;
import com.wbxnl.blog.service.base.BaseService;

import java.util.List;

/**
 * <p>
 * 资源菜单 服务类
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
public interface SystemResourceService extends BaseService< SystemResource, SystemResourceDto, SystemResourceVo> {
    /**
     * 获取资源树形结构
     * @return
     */
    List<SystemResourceDto> getResourceTree();


    /**
     * 获取所有的资源以及资源对应的角色
     * @return
     */
    List<ResourceRoleDto> getResourceRoles();

    /**
     * 获取资源树形简单结构
     * @return
     */
    List<SystemResourceDto> getResourceSimpleTree();
}
