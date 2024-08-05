package com.wbxnl.blog.api.admin.model.req;

import lombok.Data;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/8/2 16:31
 */
@Data
public class FriendLinkDataReq {

    private String username;

    private String name;

    private String icon;

    private String url;

    private String author;

    private String introduction;

    private Integer review;

    private Integer hidden;
}
