package com.wbxnl.blog.api.admin.model.res;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/8/2 16:41
 */
@Data
public class FriendLinkDetailRes {

    private Integer id;

//    private String username;

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
