package com.wbxnl.blog.api.admin.model.res;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/8/2 11:15
 */
@Data
public class CategoryDetailRes {

    private Integer id;

    private String categoryKey;

    private String name;

    private Integer hidden;

    private String description;

    private Integer articleCount;

    private List<String> articleKeys;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
