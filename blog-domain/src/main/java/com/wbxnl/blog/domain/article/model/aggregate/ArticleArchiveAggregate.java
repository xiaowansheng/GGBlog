package com.wbxnl.blog.domain.article.model.aggregate;

import com.wbxnl.blog.domain.article.model.entity.CategoryEntity;
import com.wbxnl.blog.domain.article.model.entity.TagEntity;

import java.time.LocalDateTime;
import java.util.List;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/14 6:08
 */
public class ArticleArchiveAggregate {

    private Integer id;

    private String articleKey;

    private CategoryEntity category;

    private List<TagEntity> tags;

    private String title;

    private String cover;

    private String type;

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
