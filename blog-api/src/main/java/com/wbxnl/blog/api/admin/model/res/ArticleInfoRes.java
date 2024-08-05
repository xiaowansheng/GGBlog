package com.wbxnl.blog.api.admin.model.res;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/8/2 11:02
 */
@Data
public class ArticleInfoRes {

    private Integer id;

    private String articleKey;

    private String username;

    private String categoryName;

    private List<String> tagNames;

    private String title;

    private String cover;

//    private String content;

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
