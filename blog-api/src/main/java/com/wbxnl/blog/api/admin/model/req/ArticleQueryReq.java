package com.wbxnl.blog.api.admin.model.req;

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
public class ArticleQueryReq {

    private Integer id;

    private String articleKey;

    private String categoryKey;

    private List<String> tagKey;

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
