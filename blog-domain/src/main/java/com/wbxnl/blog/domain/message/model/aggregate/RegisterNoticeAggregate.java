package com.wbxnl.blog.domain.message.model.aggregate;

import lombok.Data;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/23 14:11
 */
@Data
public class RegisterNoticeAggregate {
    private String email;
    private String username;
    private String nickname;
    private String ipAddress;
    private String ipSource;
    private byte[] coordinate;
    private String location;
}
