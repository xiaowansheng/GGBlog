package com.wbxnl.blog.domain.message.service;

import com.wbxnl.blog.domain.message.model.entity.EmailEntity;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/23 11:17
 */
public interface IEmailService {
    /**
     * 发送文本邮件
     *
     * @param emailEntity 邮件实体
     * @return 是否发送成功
     */
    boolean sentTextMail(EmailEntity emailEntity);

    /**
     * 发送HTML类型邮件
     *
     * @param emailEntity 邮件实体
     * @return 是否发送成功
     */
    boolean sentHtmlMail(EmailEntity emailEntity);

}
