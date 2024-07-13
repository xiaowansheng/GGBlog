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
public class ArticleQueryEntity {

    private Integer id;

    private Integer categoryId;

    private List<Integer> tagIds;

    private String title;

    private String content;

    private String type;

    private String originalAuthor;

    private String originalTitle;

    private String originalUrl;

    private String note;

    private Integer top;

    private String status;

    private LocalDateTime beginCreateTime;

    private LocalDateTime endCreateTime;

    private LocalDateTime beginUpdateTime;

    private LocalDateTime endUpdateTime;

    private Long wordCount;

    private Long readCount;

    private Long likeCount;

    private Long commentCount;
}
