package com.wbxnl.blog.model.vo;

import java.io.Serializable;
import java.util.List;

//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import com.wbxnl.blog.validator.custom.AccessStatus;
import com.wbxnl.blog.validator.custom.ArticleType;
import com.wbxnl.blog.validator.group.Add;
import com.wbxnl.blog.validator.group.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
//import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Range;

/**
 * <p>
 * 博客文章
 * </p>
 * TODO 校验字段还需要斟酌一下
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Schema(name = "ArticleVo",title = "博客文章",description = "提交的文章新增和修改时的数据信息")
public class ArticleVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(title = "文章编号")
    @NotNull(message = "文章ID不能为空",groups = Update.class)
    @Null(message = "文章ID必须为空",groups = Add.class)
    private Integer id;

    @Schema(title = "文章标题")
    @NotBlank(message = "文章标题不能为空",groups = {Add.class, Update.class})
    private String title;

    @Schema(title = "文章封面（需赋默认值）")
    @NotBlank(message = "文章封面不能为空")
    private String cover;

    @Schema(title = "文章内容")
    @NotBlank(message = "文章内容不能为空",groups = {Add.class})
    private String content;

    @Schema(title = "文章类型（1原创2转载3翻译4引用）")
//    @NotNull(message = "文章类型不能为空")
    @ArticleType
    private String type;

    @Schema(title = "原文作者")
    private String originalAuthor;

    @Schema(title = "原文标题")
    private String originalTitle;

    @Schema(title = "原文链接")
    private String originalUrl;

    @Schema(title = "文章备注信息")
    private String note;

    @Schema(title = "文章是否置顶显示")
    @Range(min = 0,max = 1,message = "置顶只能是0或1")
    private Integer top;

    @Schema(title = "文章类型（1展示2私密3评论可见）")
//    @NotNull(message = "文章状态设置不能为空")
    @AccessStatus
    private String status;

    @Schema(title = "文章分类")
    private CategoryVo categoryVo;

    @Schema(title = "文章标签集合")
    private List<TagVo> tagVos;

}
