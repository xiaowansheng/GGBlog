package com.wbxnl.blog.trigger.http.admin;

import com.wbxnl.blog.api.admin.model.req.FriendLinkDataReq;
import com.wbxnl.blog.api.admin.model.req.FriendLinkQueryReq;
import com.wbxnl.blog.api.admin.model.res.FriendLinkDetailRes;
import com.wbxnl.blog.api.admin.service.IFriendLinkService;
import com.wbxnl.blog.common.utils.ObjectConvertUtils;
import com.wbxnl.blog.common.vo.KeyData;
import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import com.wbxnl.blog.domain.friendLink.model.entity.FriendLinkEntity;
import com.wbxnl.blog.domain.friendLink.model.entity.FriendLinkQueryEntity;
import com.wbxnl.blog.domain.friendLink.model.entity.FriendLinkUpdateEntity;
import com.wbxnl.blog.domain.friendLink.model.vo.FriendLinkVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/8/6 11:06
 */
@Slf4j
@RestController
@RequestMapping("/admin/friend")
@RequiredArgsConstructor
public class FriendLinkController implements IFriendLinkService {

    private final com.wbxnl.blog.domain.friendLink.service.IFriendLinkService friendLinkService;

    @Override
    public KeyData addFriendLink(FriendLinkDataReq friendLinkDataReq) {
        FriendLinkVo friendLinkVo = ObjectConvertUtils.convert(friendLinkDataReq, FriendLinkVo.class);
        FriendLinkEntity friendLinkEntity = friendLinkService.addFriendLink(friendLinkVo);
        return KeyData.builder()
                .id(friendLinkEntity.getId())
                .build();
    }

    @Override
    public void updateFriendLink(FriendLinkDataReq friendLinkDataReq) {
        FriendLinkUpdateEntity friendLinkUpdateEntity = ObjectConvertUtils.convert(friendLinkDataReq, FriendLinkUpdateEntity.class);
        friendLinkService.updateFriendLink(friendLinkUpdateEntity);
    }

    @Override
    public void updateFriendLinkAuditStatus(Integer id, Integer auditStatus) {
        // TODO
    }

    @Override
    public void updateFriendLinkShowStatus(Integer id, Integer status) {
        friendLinkService.updateFriendLinkShowStatus(id, status);
    }

    @Override
    public void deleteFriendLink(Integer id) {
        friendLinkService.deleteFriendLink(id);
    }

    @Override
    public void deleteFriendLink(Integer[] ids) {
        friendLinkService.deleteFriendLink(ids);
    }

    @Override
    public PageData<FriendLinkDetailRes> getPageOfFriendLink(PageParams pageParams, FriendLinkQueryReq friendLinkQueryReq) {
        FriendLinkQueryEntity friendLinkQueryEntity = ObjectConvertUtils.convert(friendLinkQueryReq, FriendLinkQueryEntity.class);
        PageData<FriendLinkEntity> pageFriendLinks = friendLinkService.getPageFriendLinks(pageParams, friendLinkQueryEntity);
        return PageData.<FriendLinkDetailRes>builder()
                .data(ObjectConvertUtils.convertList(pageFriendLinks.getData(), FriendLinkDetailRes.class))
                .number(pageFriendLinks.getNumber())
                .size(pageFriendLinks.getSize())
                .total(pageFriendLinks.getTotal())
                .build();
    }
}
