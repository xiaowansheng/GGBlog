package com.wbxnl.blog.domain.authority.repository;

import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import com.wbxnl.blog.domain.authority.model.eneity.*;
import com.wbxnl.blog.domain.authority.model.vo.RoleMenuVo;
import com.wbxnl.blog.domain.authority.model.vo.RoleResourceVo;
import com.wbxnl.blog.domain.authority.model.vo.RoleVo;

import java.util.List;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/19 10:12
 */
public interface IRoleRepository {


    RoleEntity addRole(RoleVo roleVo);

    boolean deleteRole(Integer id);

    boolean deleteRole(Integer[] ids);

    boolean updateRole(RoleVo roleVo);

    boolean updateRoleStatus(Integer id, Integer disable);

    PageData<RoleEntity> getPageRole(PageParams pageParams, RoleQueryEntity roleQueryEntity);

    boolean updateRoleMenu(RoleMenuVo roleMenuVo);

    boolean updateRoleResource(RoleResourceVo roleResourceVo);

    PageData<SystemMenuEntity> getPageMenu(PageParams pageParams, MenuQueryEntity menuQueryEntity);

    PageData<SystemResourceEntity> getPageResource(PageParams pageParams, ResourceQueryEntity resourceQueryEntity);

    List<SystemMenuSimpleEntity> getMenuList();


    List<SystemResourceSimpleEntity> getResourceList();

    List<String> getMenuNameList(String roleName);

    List<String> getResourceNameList(String roleName);
}
