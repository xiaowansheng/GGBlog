package com.wbxnl.blog.api.front.service;

import com.wbxnl.blog.api.front.model.req.CommentDataReq;
import com.wbxnl.blog.api.front.model.res.CommentInfoRes;
import com.wbxnl.blog.common.vo.KeyData;
import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/8/5 10:32
 */
public interface ICommentService {

    /**
     * 添加评论
     * @param topicType 话题类型
     * @param topicKey 话题key
     * @param commentDataReq 评论数据
     * @return 添加结果
     */
    KeyData addComment(String topicType, String topicKey, CommentDataReq commentDataReq);

    /**
     * 获取评论
     * @param pageParams 分页参数
     * @param topicType 话题类型
     * @param topicKey 话题key
     * @return 评论
     */
    PageData<CommentInfoRes> getPageOfComments(PageParams pageParams, String topicType, String topicKey);
}
