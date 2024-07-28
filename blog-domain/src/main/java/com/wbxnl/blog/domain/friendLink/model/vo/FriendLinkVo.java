package com.wbxnl.blog.domain.friendLink.model.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/16 23:15
 */
@Data
public class FriendLinkVo {

    private String username;

    private String name;

    private String icon;

    private String url;

    private String author;

    private String introduction;

    private Integer review;

    private Integer hidden;
}
