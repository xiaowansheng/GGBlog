package com.wbxnl.blog.model.dto;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author wansheng
 * @createDate 2023/1/16 20:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
@ToString
public class MailDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String receiver;//邮件接收人
    private String subject; //邮件主题
    private String content; //邮件内容
}
