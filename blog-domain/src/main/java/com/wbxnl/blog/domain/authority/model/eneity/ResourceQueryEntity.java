package com.wbxnl.blog.domain.authority.model.eneity;

import java.time.LocalDateTime;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/18 16:58
 */
public class ResourceQueryEntity {

    private Integer id;

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
