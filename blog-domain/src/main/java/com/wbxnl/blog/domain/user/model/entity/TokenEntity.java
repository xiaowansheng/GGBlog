package com.wbxnl.blog.domain.user.model.entity;

import lombok.Builder;
import lombok.Data;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/27 23:22
 */
@Data
@Builder
public class TokenEntity {
    private String token;

    private Long expireTime;
}
