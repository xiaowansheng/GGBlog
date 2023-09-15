package com.wbxnl.blog.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.wbxnl.blog.model.dto.*;
import com.wbxnl.blog.model.entity.RoleMenu;
import com.wbxnl.blog.dao.RoleMenuDao;
import com.wbxnl.blog.model.vo.RoleMenuVo;
import com.wbxnl.blog.service.RoleMenuService;
import com.wbxnl.blog.service.impl.base.AbstractServiceImpl;
import com.wbxnl.blog.utils.ConvertUtils;
import com.wbxnl.blog.utils.RedisUtils;
import com.wbxnl.blog.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * 角色菜单 服务实现类
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Service
public class RoleMenuServiceImpl extends AbstractServiceImpl<RoleMenuDao, RoleMenu, RoleMenuDto, RoleMenuVo> implements RoleMenuService {

    @Autowired
    RoleMenuDao roleMenuDao;
    @Autowired
    RedisUtils redisUtils;


//    @Override
//    public List<RoleMenuDto> getRoleMenus(Integer roleId) {
//        return roleMenuDao.getRoleMenus(roleId);
//    }

    @Override
    public List<RouterDto> getRoutes() {
        // TODO 可以优化该查询过程
        List<RoleDto> roleDtoList = SecurityUtils.getRoleDtoList();
        Set<RouterDto> set=new HashSet<>();
        for (RoleDto role :roleDtoList) {
            for (RouterDto route : roleMenuDao.getRoutes(role.getId())) {
                set.add(route);
            }

        }
        List<RouterDto> routes = new ArrayList<>(set);
        return getMenuTree(0,routes);
    }


    /**
     * 生成菜单树
     * TODO 待优化
     * @param rootIndex
     * @param routerDtos
     * @return
     */
    private List<RouterDto> getMenuTree(int rootIndex, List<RouterDto> routerDtos) {
        List<RouterDto> root=new ArrayList<>();
        for (RouterDto routerDto:routerDtos) {
            if(routerDto.getParentId()==rootIndex){
                root.add(routerDto);
//                menuDtoList.remove(menuDto);
                List<RouterDto> menuTree = getMenuTree(routerDto.getId(), routerDtos);
                routerDto.setChildren(menuTree);
                //当当前的菜单只有一个子菜单时
                //      把子菜单的路径给根目录的重定向目录，并且把子菜单标题给父菜单标题
                //      再隐藏子菜单显示
                //      使得只显示一层菜单
                if(menuTree.size()==1){
                    routerDto.getMeta().setHideChildrenInMenu(true);
                    RouterDto childDto = menuTree.get(0);
                    routerDto.getMeta().setTitle(childDto.getMeta().getTitle());
                    routerDto.setRedirect(childDto.getPath());
                }
            }
        }
        root.sort(new Comparator<RouterDto>() {
            @Override
            public int compare(RouterDto o1, RouterDto o2) {
                return o1.getSort()- o2.getSort();
            }
        });
        return root;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateBatch(List<RoleMenuVo> roleMenuVos) {
        // TODO BUG 清空角色菜单权限时，没有可以指定的角色ID
        // 可以优化，不用每次都全部删除
        if(CollectionUtils.isEmpty(roleMenuVos)){
            return;
        }
        // 删除当前角色的菜单权限
        lambdaUpdate().eq(RoleMenu::getRoleId, roleMenuVos.get(0).getRoleId()).remove();
        // 批量新增新的菜单权限
        saveVoBatch(roleMenuVos);
    }

    @Override
    public void saveVoBatch(List<RoleMenuVo> vos) {
        super.saveVoBatch(vos);
    }
}
