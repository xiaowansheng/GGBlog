package com.wbxnl.blog.domain.authority.service;

import com.wbxnl.blog.domain.authority.model.aggregate.MenuRoleAggregate;
import com.wbxnl.blog.domain.authority.model.aggregate.ResourceRoleAggregate;
import com.wbxnl.blog.domain.authority.model.eneity.SystemMenuEntity;
import com.wbxnl.blog.domain.authority.model.eneity.SystemMenuUpdateEntity;
import com.wbxnl.blog.domain.authority.model.eneity.SystemResourceEntity;
import com.wbxnl.blog.domain.authority.model.eneity.SystemResourceUpdateEntity;
import com.wbxnl.blog.domain.authority.model.vo.SystemMenuVo;
import com.wbxnl.blog.domain.authority.model.vo.SystemResourceVo;

import java.util.List;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/18 17:51
 */
public interface IAuthorityService {

    /**
     * 添加系统菜单
     * @param systemMenuVo 系统菜单信息
     * @return 系统菜单
     */
    SystemMenuEntity addSystemMenu(SystemMenuVo systemMenuVo);

    /**
     * 添加系统
     * @param systemResourceVo 系统资源
     * @return 系统资源
     */
    SystemResourceEntity addSystemResource(SystemResourceVo systemResourceVo);

    /**
     * 更新系统菜单信息
     * @param systemResourceUpdateEntity 系统菜单信息
     */
    void updateSystemMenu(SystemMenuUpdateEntity systemResourceUpdateEntity);

    /**
     * 更新系统资源信息
     * @param systemResourceUpdateEntity 系统资源
     */
    void updateSystemResource(SystemResourceUpdateEntity systemResourceUpdateEntity);

    /**
     * 删除系统菜单
     * @param id 菜单id
     */
    void deleteSystemMenu(Integer id);

    /**
     * 删除系统菜单
     * @param ids 菜单id
     */
    void deleteSystemMenu(Integer[] ids);

    /**
     * 删除系统资源
     * @param id 资源id
     */
    void deleteSystemResource(Integer id);

    /**
     * 删除系统资源
     * @param ids 资源id
     */
    void deleteSystemResource(Integer[] ids);

    /**
     * 获取所有菜单的角色列表信息
     * @return 返回菜单角色列表
     */
    List<MenuRoleAggregate> getMenuRoleList();

    /**
     * 获取所有资源的角色列表信息
     * @return 返回资源角色列表
     */
    List<ResourceRoleAggregate> getResourceRoleList();
}
