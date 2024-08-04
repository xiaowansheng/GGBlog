package com.wbxnl.blog.api.admin.service;

import com.wbxnl.blog.api.admin.model.req.TalkDataReq;
import com.wbxnl.blog.api.admin.model.req.TalkQueryReq;
import com.wbxnl.blog.api.admin.model.res.TalkDetailRes;
import com.wbxnl.blog.common.vo.KeyData;
import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/8/2 14:22
 */
public interface ITalkService {

    /**
     * 发布说说
     * @param talkDataReq 说说数据
     * @return 说说id和key
     */
    KeyData publishTalk(TalkDataReq talkDataReq);

    /**
     * 修改说说
     * @param talkDataReq 说说数据
     */
    void updateTalk(TalkDataReq talkDataReq);

    /**
     * 修改说说置顶
     * @param id 说说id
     * @param top 置顶
     */
    void updateTalkTop(Integer id, Integer top);

    /**
     * 修改说说状态
     * @param id 说说id
     * @param status 状态
     */
    void updateTalkStatus(Integer id, String status);

    /**
     * 删除说说
     * @param id 说说id
     */
    void deleteTalk(Integer id);

    /**
     * 删除说说
     * @param ids 说说id
     */
    void deleteTalk(Integer[] ids);

    /**
     * 获取说说列表
     * @param pageParams 分页参数
     * @param talkQueryReq 查询参数
     * @return 说说列表
     */
    PageData<TalkDetailRes> getPageOfTalks(PageParams pageParams, TalkQueryReq talkQueryReq);
}
