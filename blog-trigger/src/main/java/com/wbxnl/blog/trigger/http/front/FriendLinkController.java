package com.wbxnl.blog.trigger.http.front;

import com.wbxnl.blog.api.front.model.req.FriendLinkDataReq;
import com.wbxnl.blog.api.front.model.res.FriendLinkInfoRes;
import com.wbxnl.blog.api.front.service.IFriendLinkService;
import com.wbxnl.blog.common.vo.KeyData;
import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/8/6 11:33
 */
@Slf4j
@RestController
@RequestMapping("/front/friend")
@RequiredArgsConstructor
public class FriendLinkController implements IFriendLinkService {

    private final com.wbxnl.blog.domain.friendLink.service.IFriendLinkService friendLinkService;

    @Override
    public KeyData addFriendLink(FriendLinkDataReq friendLinkDataReq) {
        return null;
    }

    @Override
    public PageData<FriendLinkInfoRes> getPageOfFriendLink(PageParams pageParams) {
        return null;
    }
}
