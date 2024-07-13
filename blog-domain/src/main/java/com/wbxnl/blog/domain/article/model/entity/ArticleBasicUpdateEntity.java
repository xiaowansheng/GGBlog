package com.wbxnl.blog.domain.article.model.entity;

import lombok.Data;

import java.util.List;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/14 5:10
 */
@Data
public class ArticleBasicUpdateEntity {

    private Integer id;

    private String articleKey;

    private CategoryVo category;

    private List<TagVo> tags;

    private String title;

    private String cover;

    private String type;

    private String originalAuthor;

    private String originalTitle;

    private String originalUrl;

    private String note;

    private Integer top;

    private String status;

    @Data
    static class CategoryVo {
        private String categoryKey;
        private String name;
    }

    @Data
    static class TagVo {
        private String tagKey;
        private String name;
    }

}
