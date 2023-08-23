package com.wbxnl.blog.service;

import com.wbxnl.blog.model.dto.RoleResourceDto;
import com.wbxnl.blog.model.dto.SystemResourceDto;
import com.wbxnl.blog.model.entity.RoleResource;
import com.wbxnl.blog.model.vo.RoleResourceVo;
import com.wbxnl.blog.service.base.BaseService;

import java.util.List;

/**
 * <p>
 * 角色资源 服务类
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
public interface RoleResourceService extends BaseService< RoleResource, RoleResourceDto, RoleResourceVo> {
//    用不到
//    /**
//     * 返回角色的资源信息
//     * @param roleId
//     * @return
//     */
//    public List<SystemResourceDto> getRoleResources(int roleId);
}
