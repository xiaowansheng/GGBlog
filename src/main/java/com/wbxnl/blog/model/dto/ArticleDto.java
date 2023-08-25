package com.wbxnl.blog.model.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
//import io.swagger.annotations.ApiModel;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 博客文章
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Schema(name = "ArticleDto",title = "ArticleDto",description = "响应的文章内容信息")
public class ArticleDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer userAuthId;

    private Integer categoryId;

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

    /**
     * 创建时间
     */
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 修改时间
     */
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 是否已删除
     */
    private Integer deleted;

    /**
     * 分类ID
     */
    private CategoryDto categoryDto;

    /**
     * 文章的标签集合
     */
    private List<TagDto> tagDtos;

    /**
     * 访问量
     */
    private Long pageView;


//    /**
//     * 点赞数
//     */
//    private Integer likeCount;

    /**
     * 评论数
     */
    private Long commentCount;
}
