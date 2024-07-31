package com.wbxnl.blog.domain.friendLink.service;

import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import com.wbxnl.blog.domain.friendLink.model.entity.FriendLinkEntity;
import com.wbxnl.blog.domain.friendLink.model.entity.FriendLinkQueryEntity;
import com.wbxnl.blog.domain.friendLink.model.entity.FriendLinkSimpleEntity;
import com.wbxnl.blog.domain.friendLink.model.entity.FriendLinkUpdateEntity;
import com.wbxnl.blog.domain.friendLink.model.vo.FriendLinkVo;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/16 23:12
 */
public interface IFriendLinkService {
    /**
     * 添加友链
     * @param friendLinkVo 友链信息
     * @return 添加结果
     */
    FriendLinkEntity addFriendLink(FriendLinkVo friendLinkVo);

    /**
     * 审核友链
     * @param id 友链id
     * @param review 审核状态
     * @return 审核结果
     */
    boolean auditFriendLink(Integer id, Integer review);

    /**
     * 更新友链
     * @param friendLinkUpdateEntity 更新信息
     * @return 更新结果
     */
    boolean updateFriendLink(FriendLinkUpdateEntity friendLinkUpdateEntity);

    /**
     * 更新友链隐藏状态
     * @param id 友链id
     * @param hidden 隐藏状态
     * @return 更新结果
     */
    boolean updateFriendLinkHidden(Integer id, Integer hidden);

    /**
     * 删除友链
     * @param id 友链id
     * @return 删除结果
     */
    boolean deleteFriendLink(Integer id);

    /**
     * 批量删除友链
     * @param ids 友链id
     * @return 删除结果
     */
    boolean deleteFriendLink(Integer[] ids);

    /**
     * 获取友链
     * @param id 友链id
     * @return 友链
     */
    FriendLinkEntity getFriendLink(Integer id);

    /**
     * 分页获取未审核的友链, 用于审核*
     * @param pageParams 分页参数
     * @param friendLinkQueryEntity 查询参数
     * @return 友链
     */
    PageData<FriendLinkEntity> getPageOfNoAuditFriendLinks(PageParams pageParams, FriendLinkQueryEntity friendLinkQueryEntity);

    /**
     * 分页获取友链
     * @param pageParams 分页参数
     * @param friendLinkQueryEntity 查询参数
     * @return 友链
     */
    PageData<FriendLinkEntity> getPageFriendLinks(PageParams pageParams, FriendLinkQueryEntity friendLinkQueryEntity);

    /**
     * 用户分页获取友链信息
     * @param pageParams 分页参数
     * @return 友链
     */
    PageData<FriendLinkSimpleEntity> getPageFriendLinksByUser(PageParams pageParams);

}
