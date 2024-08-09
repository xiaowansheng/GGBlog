package com.wbxnl.blog.api.admin.model.req;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/8/5 9:54
 */
@Data
public class SystemMenuQueryReq {

    private Integer id;

    private String menuKey;

    private String name;

    private String title;

    private String redirect;

    private String path;

    private String component;

    private Integer hidden;

    private String parentKey;

    private String perms;

    private String description;

    private LocalDateTime beginCreateTime;

    private LocalDateTime endCreateTime;
}
