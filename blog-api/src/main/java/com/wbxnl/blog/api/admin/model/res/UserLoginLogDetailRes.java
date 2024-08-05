package com.wbxnl.blog.api.admin.model.res;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/8/4 17:10
 */
@Data
public class UserLoginLogDetailRes {

    private String username;

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
