package com.wbxnl.blog.api.admin.model.req;

import lombok.Data;

import java.util.List;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/8/2 10:17
 */
@Data
public class ArticleDataReq {

    private CategoryVo category;

    private List<TagVo> tags;

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

    @Data
    public static class CategoryVo {
        private String categoryKey;
        private String name;
    }

    @Data
    public static class TagVo {
        private String tagKey;
        private String name;
    }
}
