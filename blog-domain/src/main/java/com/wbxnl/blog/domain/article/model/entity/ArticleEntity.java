package com.wbxnl.blog.domain.article.model.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/14 5:06
 */
@Data
public class ArticleEntity {

    private Integer id;

    private String articleKey;

    private String username;

    private String categoryKey;

    private String title;

    private String cover;

    private String content;

    private String type;

    private String originalAuthor;

    private String originalTitle;

    private String originalUrl;

    private String note;

    private Integer top;

    private String status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
