package com.wbxnl.blog.domain.article.model.entity;

import lombok.Data;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/14 4:46
 */
@Data
public class CategorySimpleInfoEntity {

    private Integer id;

    private Integer categoryKey;

    private String name;

    private String description;
}
