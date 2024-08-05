package com.wbxnl.blog.api.admin.model.res;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/8/4 18:20
 */
@Data
public class LeaveWordDetailRes {

    private Integer id;

    private String username;

    private String content;

    private String images;

    private String ipAddress;

    private String ipSource;

    private String device;

    private String browser;

    private byte[] point;

    private String location;

    private String type;

    private String nickname;

    private String email;

    private String qq;

    private Integer hidden;

    private Integer review;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
