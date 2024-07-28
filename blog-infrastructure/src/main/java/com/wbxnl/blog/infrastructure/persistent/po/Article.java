package com.wbxnl.blog.infrastructure.persistent.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * <p>
 * 博客文章
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Data
@Accessors(chain = true)
@TableName("t_article")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableId(value = "article_key")
    private String articleKey;

    @TableField("username")
    private String username;

    @TableField("category_key")
    private String categoryKey;

    @TableField("title")
    private String title;

    @TableField("cover")
    private String cover;

    @TableField("content")
    private String content;

    @TableField("type")
    private String type;

    @TableField("original_author")
    private String originalAuthor;

    @TableField("original_title")
    private String originalTitle;

    @TableField("original_url")
    private String originalUrl;

    @TableField("note")
    private String note;

    @TableField("top")
    private Integer top;

    @TableField("status")
    private String status;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField("deleted")
    @TableLogic
    private Integer deleted;
}
