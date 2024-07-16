package com.wbxnl.blog.domain.friendLink.model.entity;

import lombok.Data;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/16 23:19
 */
@Data
public class FriendLinkUpdateEntity {

    private Integer id;

    private String name;

    private String icon;

    private String url;

    private String author;

    private String introduction;

    private Integer review;

    private Integer hidden;
}
