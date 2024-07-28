package com.wbxnl.blog.domain.leaveMessage.model.entity;

import lombok.Data;

import java.time.LocalDateTime;


/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/17 0:08
 */
@Data
public class LeaveMessageEntity {

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
