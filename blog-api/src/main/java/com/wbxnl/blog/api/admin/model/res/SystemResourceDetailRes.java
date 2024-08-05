package com.wbxnl.blog.api.admin.model.res;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/8/5 10:04
 */
@Data
public class SystemResourceDetailRes {

    private Integer id;

    private String resourceKey;

    private String name;

    private String requestMethod;

    private String path;

    private Integer open;

    private Integer parentId;

    private String perms;

    private String description;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
