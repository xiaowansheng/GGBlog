package com.wbxnl.blog.domain.friendLink.repository;

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
 * @since 2024/7/16 23:30
 */
public interface IFriendLinkRepository {

    FriendLinkEntity addFriendLink(FriendLinkVo friendLinkVo);

    boolean auditFriendLink(Integer id, Integer review);

    FriendLinkEntity getFriendLink(Integer id);

    boolean deleteFriendLink(Integer id);

    boolean deleteFriendLink(Integer[] ids);

    boolean updateFriendLink(FriendLinkUpdateEntity friendLinkUpdateEntity);

    boolean updateFriendLinkHidden(Integer id, Integer hidden);

    PageData<FriendLinkEntity> getPageFriendLinks(PageParams pageParams, FriendLinkQueryEntity friendLinkQueryEntity);

    PageData<FriendLinkEntity> getPageOfNoAuditFriendLinks(PageParams pageParams, FriendLinkQueryEntity friendLinkQueryEntity);

    PageData<FriendLinkSimpleEntity> getPageFriendLinksByUser(PageParams pageParams);
}
