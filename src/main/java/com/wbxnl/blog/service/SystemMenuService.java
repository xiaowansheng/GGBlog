package com.wbxnl.blog.service;

import com.wbxnl.blog.model.dto.SystemMenuDto;
import com.wbxnl.blog.model.entity.SystemMenu;
import com.wbxnl.blog.model.vo.SystemMenuVo;
import com.wbxnl.blog.service.base.BaseService;

import java.util.List;

/**
 * <p>
 * 菜单目录 服务类
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
public interface SystemMenuService extends BaseService<SystemMenu, SystemMenuDto, SystemMenuVo> {

    /**
     * 获取菜单列表树形结构数据
     * @return
     */
    List<SystemMenuDto> getMenuTree();

    /**
     * 获取菜单列表树形结构简单数据
     * @return
     */
    List<SystemMenuDto> getMenuSimpleTree();
}
