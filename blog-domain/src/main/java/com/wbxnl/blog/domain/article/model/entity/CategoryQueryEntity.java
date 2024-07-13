package com.wbxnl.blog.domain.article.model.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/14 4:52
 */
@Data
public class CategoryQueryEntity {

    private Integer id;

    private String categoryKey;

    private String name;

    private String description;

    private LocalDateTime beginCreateTime;

    private LocalDateTime endCreateTime;
}
