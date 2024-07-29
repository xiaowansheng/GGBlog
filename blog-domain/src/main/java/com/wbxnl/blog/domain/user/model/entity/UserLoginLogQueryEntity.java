package com.wbxnl.blog.domain.user.model.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/21 1:15
 */
@Data
public class UserLoginLogQueryEntity {

    private Integer id;

    private String username;

//    private String email;

    private String nickname;

//    private String avatar;

    private String ipAddress;

    private String ipSource;

    private String device;

    private String browser;

//    private byte[] point;

    private String location;

    private LocalDateTime beginCreateTime;

    private LocalDateTime endCreateTime;
}
