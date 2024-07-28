package com.wbxnl.blog.domain.user.model.aggregate;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/21 1:12
 */
@Data
public class UserLoginLogAggregate {

    private Integer userAuthId;

    private Integer username;

    private String email;

    private String nickname;

    private String avatar;

    private String ipAddress;

    private String ipSource;

    private String device;

    private String browser;

    private byte[] point;

    private String location;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
