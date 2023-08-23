package com.wbxnl.blog.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 文章对应标签
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
@TableName("t_article_tag")
//@ApiModel(value = "ArticleTag对象", description = "文章对应标签")
public class ArticleTag implements Serializable {

    private static final long serialVersionUID = 1L;

    ////@ApiModelProperty("文章和标签关联ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    ////@ApiModelProperty("文章ID")
    @TableField("article_id")
    private Integer articleId;

    ////@ApiModelProperty("标签ID")
    @TableField("tag_id")
    private Integer tagId;


}
