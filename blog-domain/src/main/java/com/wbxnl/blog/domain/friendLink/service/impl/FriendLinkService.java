package com.wbxnl.blog.domain.friendLink.service.impl;

import com.wbxnl.blog.common.enums.OperationCodeEnum;
import com.wbxnl.blog.common.exception.BlogException;
import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import com.wbxnl.blog.domain.friendLink.model.entity.FriendLinkEntity;
import com.wbxnl.blog.domain.friendLink.model.entity.FriendLinkQueryEntity;
import com.wbxnl.blog.domain.friendLink.model.entity.FriendLinkSimpleEntity;
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
    public void auditFriendLink(Integer id, Integer review) {
        boolean updated = friendLinkRepository.auditFriendLink(id, review);
        if (!updated) {
            throw new BlogException(OperationCodeEnum.UPDATE_FAILURE);
        }
    }

    @Override
    public void updateFriendLink(FriendLinkUpdateEntity friendLinkUpdateEntity) {
        boolean updated = friendLinkRepository.updateFriendLink(friendLinkUpdateEntity);
        if (!updated) {
            throw new BlogException(OperationCodeEnum.UPDATE_FAILURE);
        }
    }

    @Override
    public void updateFriendLinkShowStatus(Integer id, Integer hidden) {
        boolean updated = friendLinkRepository.updateFriendLinkHidden(id, hidden);
        if (!updated) {
            throw new BlogException(OperationCodeEnum.UPDATE_FAILURE);
        }
    }

    @Override
    public void deleteFriendLink(Integer id) {
        boolean updated = friendLinkRepository.deleteFriendLink(id);
        if (!updated) {
            throw new BlogException(OperationCodeEnum.DELETE_FAILURE);
        }
    }

    @Override
    public void deleteFriendLink(Integer[] ids) {
        boolean updated = friendLinkRepository.deleteFriendLink(ids);
        if (!updated) {
            throw new BlogException(OperationCodeEnum.DELETE_FAILURE);
        }
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

    @Override
    public PageData<FriendLinkSimpleEntity> getPageFriendLinksByUser(PageParams pageParams) {
        return friendLinkRepository.getPageFriendLinksByUser(pageParams);
    }
}
