package com.wbxnl.blog.dao;

import com.wbxnl.blog.model.dto.ResourceRoleDto;
import com.wbxnl.blog.model.dto.SystemResourceDto;
import com.wbxnl.blog.model.entity.SystemResource;
import com.wbxnl.blog.dao.base.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 资源菜单 Mapper 接口
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Mapper
public interface SystemResourceDao extends BaseDao<SystemResource> {


    /**
     * 获取所有的资源以及资源对应的角色
     * @return
     */
    List<ResourceRoleDto> getResourceRoles();
}
