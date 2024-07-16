package com.wbxnl.blog.domain.friendLink.model.entity;

import lombok.Data;

import java.time.LocalDateTime;


/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/16 23:12
 */
@Data
public class FriendLinkEntity {

    private Integer id;

    private Integer userAuthId;

    private String name;

    private String icon;

    private String url;

    private String author;

    private String introduction;

    private Integer review;

    private Integer hidden;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
