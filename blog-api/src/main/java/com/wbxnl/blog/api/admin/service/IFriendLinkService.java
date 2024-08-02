package com.wbxnl.blog.api.admin.service;

import com.wbxnl.blog.api.admin.model.req.FriendLinkDataReq;
import com.wbxnl.blog.api.admin.model.req.FriendLinkQueryReq;
import com.wbxnl.blog.api.admin.model.res.FriendLinkDetailRes;
import com.wbxnl.blog.common.vo.KeyData;
import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/8/2 16:24
 */
public interface IFriendLinkService {

    /**
     * 添加友链
     * @param friendLinkDataReq 友链1
     * @return KeyData
     */
    KeyData addFriendLink(FriendLinkDataReq friendLinkDataReq);

    /**
     * 删除友链
     * @param friendLinkDataReq 友链数据
     * @return Void
     */
    Void updateFriendLink(FriendLinkDataReq friendLinkDataReq);


    /**
     * 更新友链审核状态
     * @param id 友链id
     * @param auditStatus 审核状态
     * @return Void
     */
    Void updateFriendLinkAuditStatus(Integer id, Integer auditStatus);

    /**
     * 更新友链状态
     * @param id 友链id
     * @param status 状态
     * @return Void
     */
    Void updateFriendLinkShowStatus(Integer id, Integer status);

    /**
     * 删除友链
     * @param id 友链id
     * @return Void
     */
    Void deleteFriendLink(Integer id);

    /**
     * 删除友链
     * @param ids 友链id
     * @return Void
     */
    Void deleteFriendLink(Integer[] ids);

    /**
     * 获取友链分页数据
     * @param pageParams 分页参数
     * @param friendLinkQueryReq 友链查询条件
     * @return 友链分页数据
     */
    PageData<FriendLinkDetailRes> getPageOfFriendLink(PageParams pageParams, FriendLinkQueryReq friendLinkQueryReq);

}
