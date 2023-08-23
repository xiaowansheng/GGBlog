package com.wbxnl.blog.service.impl;

import com.wbxnl.blog.model.dto.SystemMenuDto;
import com.wbxnl.blog.model.entity.RoleMenu;
import com.wbxnl.blog.model.entity.SystemMenu;
import com.wbxnl.blog.dao.SystemMenuDao;
import com.wbxnl.blog.model.vo.SystemMenuVo;
import com.wbxnl.blog.service.RoleMenuService;
import com.wbxnl.blog.service.SystemMenuService;
import com.wbxnl.blog.service.impl.base.AbstractServiceImpl;
import com.wbxnl.blog.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * <p>
 * 菜单目录 服务实现类
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Service
public class SystemMenuServiceImpl extends AbstractServiceImpl<SystemMenuDao, SystemMenu, SystemMenuDto, SystemMenuVo> implements SystemMenuService {


    @Autowired
    RoleMenuService roleMenuService;

    @Override
    public List<SystemMenuDto> getMenuTree() {
        List<SystemMenu> menuList = list();
        List<SystemMenuDto> menuDtoList = ConvertUtils.sourceToTarget(menuList, SystemMenuDto.class);
        List<SystemMenuDto> menuTree = getMenuTree(0, menuDtoList);
        return menuTree;
    }

    /**
     * 递归遍历使链表形成树形结构
     *
     * @param rootIndex   父节点ID，根节点默认为0
     * @param menuDtoList
     * @return
     */
    private List<SystemMenuDto> getMenuTree(int rootIndex, List<SystemMenuDto> menuDtoList) {
        // TODO 或许还能优化，先标记
        List<SystemMenuDto> root = new ArrayList<>();
        for (SystemMenuDto menuDto : menuDtoList) {
            if (menuDto.getParentId() == rootIndex) {
                root.add(menuDto);
//                menuDtoList.remove(menuDto);
                List<SystemMenuDto> menuTree = getMenuTree(menuDto.getId(), menuDtoList);
                menuDto.setChildren(menuTree);
            }
        }
        root.sort(new Comparator<SystemMenuDto>() {
            @Override
            public int compare(SystemMenuDto o1, SystemMenuDto o2) {
                return o1.getSort() - o2.getSort();
            }
        });
        return root;
    }

    @Transactional
    @Override
    public void deleteById(Integer id) {
        // 删除与该菜单相关的数据
        // 删除角色与该菜单的关联数据
        roleMenuService.lambdaUpdate().eq(RoleMenu::getMenuId, id).remove();
        // 再删除菜单
        removeById(id);
    }
}
