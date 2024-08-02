package com.wbxnl.blog.api.front.service;

import com.wbxnl.blog.api.front.model.req.FriendLinkDataReq;
import com.wbxnl.blog.api.front.model.res.FriendLinkInfoRes;
import com.wbxnl.blog.common.vo.KeyData;
import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/8/2 16:45
 */
public interface IFriendLinkService {
    /**
     * 添加友链
     * @param friendLinkDataReq 友链数据
     * @return 友链key
     */
    KeyData addFriendLink(FriendLinkDataReq friendLinkDataReq);

    /**
     * 分页获取友链信息
     * @param pageParams 分页参数
     * @return 友链信息
     */
    PageData<FriendLinkInfoRes> getPageOfFriendLink(PageParams pageParams);
}
