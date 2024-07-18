package com.wbxnl.blog.domain.config.model.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/17 10:45
 */
@Data
public class SystemConfigEntity {

    private Integer id;

    private String name;

    private String label;

    private String value;

    private String description;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
