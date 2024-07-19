package com.wbxnl.blog.domain.authority.repository;

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
 * @since 2024/7/19 9:12
 */
public interface IAuthorityRepository {

    SystemMenuEntity addSystemMenu(SystemMenuVo systemMenuVo);

    SystemResourceEntity addSystemResource(SystemResourceVo systemResourceVo);

    boolean updateSystemMenu(SystemMenuUpdateEntity systemResourceUpdateEntity);

    boolean updateSystemResource(SystemResourceUpdateEntity systemResourceUpdateEntity);

    boolean deleteSystemMenu(Integer id);

    boolean deleteSystemMenu(Integer[] ids);

    boolean deleteSystemResource(Integer id);

    boolean deleteSystemResource(Integer[] ids);

    List<MenuRoleAggregate> getMenuRoleList();

    List<ResourceRoleAggregate> getResourceRoleList();
}
