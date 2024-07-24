package com.wbxnl.blog.domain.message.service;

import com.wbxnl.blog.domain.message.model.aggregate.*;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/23 14:08
 */
public interface INoticeService {
    /**
     * 发送验证码通知
     * @param captchaNoticeAggregate 验证码通知
     */
    void sendCaptchaNotice(CaptchaNoticeAggregate captchaNoticeAggregate);
    /**
     * 发送注册通知
     * @param registerNoticeAggregate 注册通知
     */
    void sendRegisterNotice(RegisterNoticeAggregate registerNoticeAggregate);

    /**
     * 发送登录通知
     * @param loginNoticeAggregate 登录通知
     */
    void sendLoginNotice(LoginNoticeAggregate loginNoticeAggregate);

    /**
     * 发送留言通知
     * @param leaveWordNoticeAggregate 留言通知
     */
    void sendLeaveWordNotice(LeaveWordNoticeAggregate leaveWordNoticeAggregate);

    /**
     * 发送评论通知
     * @param commentNoticeAggregate 评论通知
     */
    void sendCommentNotice(CommentNoticeAggregate commentNoticeAggregate);
}
