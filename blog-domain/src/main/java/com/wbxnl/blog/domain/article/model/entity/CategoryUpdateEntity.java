package com.wbxnl.blog.domain.article.model.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/14 2:41
 */
@Data
public class CategoryUpdateEntity {

    private Integer id;

    private String name;

    private String description;
}
