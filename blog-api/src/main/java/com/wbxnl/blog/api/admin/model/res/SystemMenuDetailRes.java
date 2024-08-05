package com.wbxnl.blog.api.admin.model.res;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/8/5 9:53
 */
@Data
public class SystemMenuDetailRes {

    private Integer id;

    private String menuKey;

    private String title;

    private String icon;

    private String redirect;

    private String path;

    private String component;

    private Integer hidden;

    private Byte sort;

    private Integer parentId;

    private String perms;

    private String description;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
