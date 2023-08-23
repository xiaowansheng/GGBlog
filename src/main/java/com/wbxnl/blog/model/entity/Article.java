package com.wbxnl.blog.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
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
@Accessors(chain = true)
@TableName("t_article")
//@ApiModel(value = "Article对象", description = "博客文章")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    // // @ApiModelProperty("文章编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    // // @ApiModelProperty("作者ID（与账号表关联）")
    @TableField("user_auth_id")
    private Integer userAuthId;

    // // @ApiModelProperty("分类ID")
    @TableField("category_id")
    private Integer categoryId;

    // // @ApiModelProperty("文章标题")
    @TableField("title")
    private String title;

    // // @ApiModelProperty("文章封面（需赋默认值）")
    @TableField("cover")
    private String cover;

    // // @ApiModelProperty("文章内容")
    @TableField("content")
    private String content;

    // // @ApiModelProperty("文章类型（1原创2转载3翻译4引用）")
    @TableField("type")
    private String type;

    // // @ApiModelProperty("原文作者")
    @TableField("original_author")
    private String originalAuthor;

    // // @ApiModelProperty("原文标题")
    @TableField("original_title")
    private String originalTitle;

    // // @ApiModelProperty("原文链接")
    @TableField("original_url")
    private String originalUrl;

    // // @ApiModelProperty("文章备注信息")
    @TableField("note")
    private String note;

    // // @ApiModelProperty("文章是否置顶显示")
    @TableField("top")
    private Integer top;

    // // @ApiModelProperty("文章状态（1展示2私密3评论可见）")
    @TableField("status")
    private String status;

    ////@ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    ////@ApiModelProperty("修改时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    ////@ApiModelProperty("是否已删除")
    @TableField("deleted")
    @TableLogic
    private Integer deleted;
}
