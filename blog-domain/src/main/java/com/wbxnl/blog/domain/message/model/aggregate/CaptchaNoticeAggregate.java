package com.wbxnl.blog.domain.message.model.aggregate;

import lombok.Data;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/23 14:39
 */
@Data
public class CaptchaNoticeAggregate {
    private String email;
    private String code;
    private Long expireTime;
}
