package com.wbxnl.blog.domain.message.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/23 11:18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailEntity {
    /**
     * 邮件接收人
     */
    private String receiver;
    /**
     * 邮件主题
     */
    private String subject;
    /**
     * 邮件内容
     */
    private String content;
}
