package com.wbxnl.blog.domain.leaveMessage.model.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/17 0:10
 */
@Data
public class LeaveMessageQueryEntity {

    private Integer id;

    private Integer userAuthId;

    private String content;

//    private String images;

    private String ipAddress;

    private String ipSource;

    private String device;

    private String browser;

    private byte[] point;

    private Integer distance;

    private String location;

    private String type;

    private String nickname;

    private String email;

    private String qq;

    private Integer hidden;

    private Integer review;

    private LocalDateTime beginCreateTime;

    private LocalDateTime endCreateTime;

    private LocalDateTime beginUpdateTime;

    private LocalDateTime endUpdateTime;
}
