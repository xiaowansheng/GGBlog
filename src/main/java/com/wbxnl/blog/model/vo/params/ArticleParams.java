package com.wbxnl.blog.model.vo.params;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author xiaowansheng
 * @Date 2023/8/12 22:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleParams implements QueryParams{
    private static final long serialVersionUID = 1L;

    @Parameter(description = "文章编号")
    private Integer id;

    @Parameter(description = "作者ID（与账号表关联）")
    private Integer userAuthId;

    @Parameter(description = "分类ID")
    private Integer categoryId;

    @Parameter(description = "标签ID")
    private Integer tagId;

    @Parameter(description = "文章标题")
    private String title;

    @Parameter(description = "文章内容")
    private String content;

    @Parameter(description = "文章类型（1原创2转载3翻译4引用）")
    private String type;

    @Parameter(description = "原文作者")
    private String originalAuthor;

    @Parameter(description = "原文标题")
    private String originalTitle;

    @Parameter(description = "文章备注信息")
    private String note;

    @Parameter(description = "文章是否置顶显示")
    private Integer top;

    @Parameter(description = "文章状态（1展示2私密3评论可见）")
    private String status;

    @Parameter(description = "时间区间：开始时间")
    private Date beginDate;

    @Parameter(description = "时间区间：末尾时间")
    private Date endDate;

}
