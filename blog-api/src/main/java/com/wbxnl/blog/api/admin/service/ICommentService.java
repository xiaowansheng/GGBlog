package com.wbxnl.blog.api.admin.service;

import com.wbxnl.blog.api.admin.model.req.CommentQueryReq;
import com.wbxnl.blog.api.admin.model.res.CommentDetailRes;
import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/8/5 10:24
 */
public interface ICommentService {

    /**
     * 获取评论列表
     * @param pageParams 分页参数
     * @param commentQueryReq 评论查询条件
     * @return 评论列表
     */
    PageData<CommentDetailRes> getPageOfComments(PageParams pageParams, CommentQueryReq commentQueryReq);

    /**
     * 更新评论审核状态
     * @param id 评论id
     * @param auditStatus 审核状态
     */
    void updateCommentAuditStatus(Integer id, Integer auditStatus);

    /**
     * 更新评论置顶状态
     * @param id 评论id
     * @param top 置顶状态
     */
    void updateCommentTop(Integer id, Integer top);

    /**
     * 更新评论状态
     * @param id 评论id
     * @param status 评论显示状态
     */
    void updateCommentShowStatus(Integer id, Integer status);

    /**
     * 删除评论
     * @param id 评论id
     */
    void deleteComment(Integer id);

    /**
     * 删除评论
     * @param ids 评论id
     */
    void deleteComment(Integer[] ids);
}
