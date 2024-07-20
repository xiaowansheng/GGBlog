package com.wbxnl.blog.domain.article.model.vo;

import lombok.Data;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/21 0:36
 */
@Data
public class ArticleDraftVo {

    private Integer id;

//    private String articleKey;

    private Integer userAuthId;

//    private String categoryKey;

    private String title;

//    private String cover;

    private String content;

    private String type;
//
//    private String originalAuthor;
//
//    private String originalTitle;
//
//    private String originalUrl;
//
//    private String note;
//
//    private Integer top;
//
//    private String status;
}
