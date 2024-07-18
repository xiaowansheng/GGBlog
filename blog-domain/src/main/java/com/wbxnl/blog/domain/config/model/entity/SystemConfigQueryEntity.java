package com.wbxnl.blog.domain.config.model.entity;

import lombok.Data;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/17 10:48
 */
@Data
public class SystemConfigQueryEntity {

    private Integer id;

    private String name;

    private String label;

    private String value;

    private String description;
}
