package com.wbxnl.blog.service.impl;

import com.wbxnl.blog.filter.AuthorizationManagerImpl;
import com.wbxnl.blog.model.dto.RoleDto;
import com.wbxnl.blog.model.entity.Role;
import com.wbxnl.blog.dao.RoleDao;
import com.wbxnl.blog.model.entity.RoleMenu;
import com.wbxnl.blog.model.entity.RoleResource;
import com.wbxnl.blog.model.entity.UserRole;
import com.wbxnl.blog.model.vo.RoleVo;
import com.wbxnl.blog.service.RoleMenuService;
import com.wbxnl.blog.service.RoleResourceService;
import com.wbxnl.blog.service.RoleService;
import com.wbxnl.blog.service.UserRoleService;
import com.wbxnl.blog.service.impl.base.AbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 用户角色 服务实现类
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Service
public class RoleServiceImpl extends AbstractServiceImpl<RoleDao, Role, RoleDto, RoleVo> implements RoleService {

    @Autowired
    RoleMenuService roleMenuService;
    @Autowired
    RoleResourceService roleResourceService;
    @Autowired
    UserRoleService userRoleService;

    @Autowired
    private AuthorizationManagerImpl authorizationManager;

    @Override
    public void update(RoleVo vo) {
        super.update(vo);
        //如果是其它属性为空，只要可用状态不空，则为修改角色可用状态，修改则需要调整资源权限
        //则重新加载权限集合
        if(vo.getDisable()!=null){
            authorizationManager.clearDataSource();
        }
    }

    @Transactional
    @Override
    public void deleteById(Integer id) {
        //删除角色相关的其它信息
        //删除用户角色
        userRoleService.lambdaUpdate().eq(UserRole::getRoleId,id).remove();
        //删除角色菜单权限
        roleMenuService.lambdaUpdate().eq(RoleMenu::getRoleId,id).remove();
        //删除角色接口权限
        roleResourceService.lambdaUpdate().eq(RoleResource::getRoleId,id).remove();
        removeById(id);
        //重新加载接口权限
        authorizationManager.clearDataSource();
    }
}
