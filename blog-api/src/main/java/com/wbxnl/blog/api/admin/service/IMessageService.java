package com.wbxnl.blog.api.admin.service;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/8/4 17:21
 */
public interface IMessageService {

    /**
     * 发送验证码
     * @param email 邮箱
     */
    void sendVerifyCode(String email);

    /**
     * 发送邮件
     * @param email 邮箱
     * @param title 标题
     * @param content 内容
     */
    void sendMessage(String email, String title, String content);
}
