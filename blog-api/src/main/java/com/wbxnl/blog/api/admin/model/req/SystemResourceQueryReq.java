package com.wbxnl.blog.api.admin.model.req;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/8/5 10:04
 */
@Data
public class SystemResourceQueryReq {

    private Integer id;

    private String resourceKey;

    private String name;

    private String requestMethod;

    private String path;

    private Integer open;

    private String parentKey;

    private String perms;

    private String description;

    private LocalDateTime beginCreateTime;

    private LocalDateTime endCreateTime;
}
