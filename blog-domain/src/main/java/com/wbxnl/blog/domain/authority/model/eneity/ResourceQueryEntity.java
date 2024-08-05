package com.wbxnl.blog.domain.authority.model.eneity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/18 16:58
 */
@Data
public class ResourceQueryEntity {

    private Integer id;

    private String resourceKey;

    private String name;

    private String requestMethod;

    private String path;

    private Integer open;

    private Integer parentId;

    private String perms;

    private String description;

    private LocalDateTime beginCreateTime;

    private LocalDateTime endCreateTime;
}
