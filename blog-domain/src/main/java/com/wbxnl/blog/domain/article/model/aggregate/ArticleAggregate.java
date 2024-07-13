package com.wbxnl.blog.domain.article.model.aggregate;

import com.wbxnl.blog.domain.article.model.entity.CategoryEntity;
import com.wbxnl.blog.domain.article.model.entity.TagEntity;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/14 5:47
 */
@Data
public class ArticleAggregate {

    private Integer id;

    private Integer userAuthId;

    private CategoryEntity category;

    private List<TagEntity> tags;

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

    private Long wordCount;

    private Long readCount;

    private Long likeCount;

    private Long commentCount;
}
