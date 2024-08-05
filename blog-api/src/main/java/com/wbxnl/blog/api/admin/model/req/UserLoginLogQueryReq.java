package com.wbxnl.blog.api.admin.model.req;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/8/4 17:10
 */
@Data
public class UserLoginLogQueryReq {

    private Integer id;

    private String username;

    private String email;

    private String nickname;

    private String ipAddress;

    private String ipSource;

    private String device;

    private String browser;

    private byte[] point;

    private String location;

    private LocalDateTime beginCreateTime;

    private LocalDateTime endCreateTime;
}
