package com.wbxnl.blog.domain.article.model.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/14 5:06
 */
@Data
public class ArticleQueryByVisitorEntity {

    private Integer id;

    private String categoryKey;

    private String tagKey;
}
