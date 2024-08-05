package com.wbxnl.blog.api.admin.model.res;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/8/2 15:07
 */
@Data
public class ConfigDetailRes {

    private Integer id;

    private String configKey;

    private String name;

    private String value;

    private String description;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
