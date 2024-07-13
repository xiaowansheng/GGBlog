package com.wbxnl.blog.domain.article.model.entity;

import lombok.Data;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/14 4:46
 */
@Data
public class TagSimpleInfoEntity {

    private Integer id;

    private Integer tagKey;

    private String name;

    private String description;
}
