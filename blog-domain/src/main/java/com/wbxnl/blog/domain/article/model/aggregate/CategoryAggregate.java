package com.wbxnl.blog.domain.article.model.aggregate;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/14 4:03
 */
@Data
public class CategoryAggregate {

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
