package com.wbxnl.blog.domain.authority.model.eneity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/19 9:59
 */
@Data
public class SystemResourceUpdateEntity {

    private Integer id;

    private String resourceKey;

    private String name;

    private String requestMethod;

    private String path;

    private Integer open;

    private Integer parentId;

    private String perms;

    private String description;
}
