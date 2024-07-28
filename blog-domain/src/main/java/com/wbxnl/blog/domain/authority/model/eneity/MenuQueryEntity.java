package com.wbxnl.blog.domain.authority.model.eneity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/18 17:19
 */
@Data
public class MenuQueryEntity {
    private Integer id;

    private String menuKey;

    private String name;

    private String title;

    private String redirect;

    private String path;

    private String component;

    private Integer hidden;

    private Integer parentId;

    private String perms;

    private String description;

    private LocalDateTime beginCreateTime;

    private LocalDateTime endCreateTime;
}
