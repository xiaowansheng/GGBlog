package com.wbxnl.blog.domain.article.model.aggregate;

import lombok.Data;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/14 4:03
 */
@Data
public class CategorySimpleAggregate {

    private Integer id;

    private String categoryKey;

    private String name;

    private String description;

    private Integer articleCount;
}
