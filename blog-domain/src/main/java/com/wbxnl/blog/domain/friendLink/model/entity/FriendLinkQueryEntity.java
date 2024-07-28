package com.wbxnl.blog.domain.friendLink.model.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/16 23:22
 */
@Data
public class FriendLinkQueryEntity {
    private Integer id;

    private String username;

    private String name;

//    private String icon;

    private String url;

    private String author;

    private String introduction;

    private Integer review;

    private Integer hidden;

    private LocalDateTime beginCreateTime;

    private LocalDateTime endCreateTime;

    private LocalDateTime beginUpdateTime;

    private LocalDateTime endUpdateTime;
}
