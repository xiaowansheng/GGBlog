package com.wbxnl.blog.api.admin.model.req;

import lombok.Data;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/8/5 10:03
 */
@Data
public class SystemResourceDataReq {

    private String resourceKey;

    private String requestMethod;

    private String path;

    private Integer open;

    private String parentKey;

    private String perms;

    private String description;
}
