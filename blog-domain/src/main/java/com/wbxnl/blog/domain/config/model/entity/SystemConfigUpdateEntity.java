package com.wbxnl.blog.domain.config.model.entity;

import lombok.Data;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/17 10:45
 */
@Data
public class SystemConfigUpdateEntity {

    private Integer id;

    private String configKey;

    private String name;

    private String value;

    private String description;

}
