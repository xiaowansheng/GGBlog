package com.wbxnl.blog.domain.authority.model.eneity;

import java.time.LocalDateTime;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/18 16:47
 */
public class SystemMenuEntity {

    private Integer id;

    private String menuKey;

//    private String name;

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
