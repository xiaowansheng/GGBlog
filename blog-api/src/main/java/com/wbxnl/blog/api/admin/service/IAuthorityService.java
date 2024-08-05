package com.wbxnl.blog.api.admin.service;

import com.wbxnl.blog.api.admin.model.req.SystemMenuDataReq;
import com.wbxnl.blog.api.admin.model.req.SystemMenuQueryReq;
import com.wbxnl.blog.api.admin.model.req.SystemResourceDataReq;
import com.wbxnl.blog.api.admin.model.req.SystemResourceQueryReq;
import com.wbxnl.blog.api.admin.model.res.SystemMenuDetailRes;
import com.wbxnl.blog.api.admin.model.res.SystemResourceDetailRes;
import com.wbxnl.blog.common.vo.KeyData;
import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/8/5 9:45
 */
public interface IAuthorityService {
    /**
     * 添加菜单
     * @param systemMenuDataReq 菜单数据
     * @return 菜单数据
     */
    KeyData addSystemMenu(SystemMenuDataReq systemMenuDataReq);

    /**
     * 更新菜单
     * @param systemMenuDataReq 菜单数据
     */
    void updateSystemMenu(SystemMenuDataReq systemMenuDataReq);

    /**
     * 更新菜单状态
     * @param id 菜单id
     * @param status 菜单状态
     */
    void updateSystemMenuStatus(Integer id, Integer status);

    /**
     * 删除菜单
     * @param id 菜单id
     */
    void deleteSystemMenu(Integer id);

    /**
     * 删除菜单
     * @param ids 菜单id
     */
    void deleteSystemMenu(Integer[] ids);

    /**
     * 分页查询菜单
     * @param pageParams 分页参数
     * @param systemMenuQueryReq 查询参数
     * @return 分页数据
     */
    PageData<SystemMenuDetailRes> getPageOfSystemMenu(PageParams pageParams, SystemMenuQueryReq systemMenuQueryReq);

    /**
     * 添加资源
     * @param systemMenuDataReq 资源数据
     * @return 资源数据
     */
    KeyData addSystemResource(SystemResourceDataReq systemMenuDataReq);

    /**
     * 更新资源
     * @param systemResourceDataReq 资源数据
     */
    void updateSystemResource(SystemResourceDataReq systemResourceDataReq);

    /**
     * 更新资源状态
     * @param id 资源id
     * @param status 资源状态
     */
    void updateSystemResourceStatus(Integer id, Integer status);

    /**
     * 删除资源
     * @param id 资源id
     */
    void deleteSystemResource(Integer id);

    /**
     * 删除资源
     * @param ids 资源id
     */
    void deleteSystemResource(Integer[] ids);

    /**
     * 分页查询资源
     * @param pageParams 分页参数
     * @param systemResourceQueryReq 查询参数
     * @return 分页数据
     */
    PageData<SystemResourceDetailRes> getPageOfSystemResource(PageParams pageParams, SystemResourceQueryReq systemResourceQueryReq);
}
