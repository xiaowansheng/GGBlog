package com.wbxnl.blog.api.admin.model.req;

import lombok.Data;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/8/2 14:54
 */
@Data
public class ConfigDataReq {

    private Integer id;

    private String configKey;

    private String name;

    private String value;

    private String description;
}
