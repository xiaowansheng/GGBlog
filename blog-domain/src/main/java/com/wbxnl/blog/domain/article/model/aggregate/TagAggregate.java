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
public class TagAggregate {

    private Integer id;

    private String tagKey;

    private String name;

    private String description;

    private Integer articleCount;

    private List<String> articleKeys;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
