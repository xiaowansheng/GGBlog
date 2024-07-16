package com.wbxnl.blog.domain.friendLink.service.impl;

import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import com.wbxnl.blog.domain.friendLink.model.entity.FriendLinkEntity;
import com.wbxnl.blog.domain.friendLink.model.entity.FriendLinkQueryEntity;
import com.wbxnl.blog.domain.friendLink.model.entity.FriendLinkUpdateEntity;
import com.wbxnl.blog.domain.friendLink.model.vo.FriendLinkVo;
import com.wbxnl.blog.domain.friendLink.repository.IFriendLinkRepository;
import com.wbxnl.blog.domain.friendLink.service.IFriendLinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/16 23:23
 */
@Service
@RequiredArgsConstructor
public class FriendLinkService implements IFriendLinkService {

    private final IFriendLinkRepository friendLinkRepository;

    @Override
    public FriendLinkEntity addFriendLink(FriendLinkVo friendLinkVo) {
        return friendLinkRepository.addFriendLink(friendLinkVo);
    }

    @Override
    public boolean auditFriendLink(Integer id, Integer review) {
        return friendLinkRepository.auditFriendLink(id, review);
    }

    @Override
    public boolean updateFriendLink(FriendLinkUpdateEntity friendLinkUpdateEntity) {
        return friendLinkRepository.updateFriendLink(friendLinkUpdateEntity);
    }

    @Override
    public boolean updateFriendLinkHidden(Integer id, Integer hidden) {
        return friendLinkRepository.updateFriendLinkHidden(id, hidden);
    }

    @Override
    public boolean deleteFriendLink(Integer id) {
        return friendLinkRepository.deleteFriendLink(id);
    }

    @Override
    public boolean deleteFriendLink(Integer[] ids) {
        return friendLinkRepository.deleteFriendLink(ids);
    }

    @Override
    public FriendLinkEntity getFriendLink(Integer id) {
        return friendLinkRepository.getFriendLink(id);
    }

    @Override
    public PageData<FriendLinkEntity> getPageOfNoAuditFriendLinks(PageParams pageParams, FriendLinkQueryEntity friendLinkQueryEntity) {
        return friendLinkRepository.getPageOfNoAuditFriendLinks(pageParams, friendLinkQueryEntity);
    }

    @Override
    public PageData<FriendLinkEntity> getPageFriendLinks(PageParams pageParams, FriendLinkQueryEntity friendLinkQueryEntity) {
        return friendLinkRepository.getPageFriendLinks(pageParams, friendLinkQueryEntity);
    }
}
