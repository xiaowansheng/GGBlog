package com.wbxnl.blog.model.dto.extra;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 主页展示的博客数据统计
 * @Author xiaowansheng
 * @Date 2023/8/13 0:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "StatisticsOfNumberDto",description = "文章、分类、标签数量统计")
public class StatisticsOfNumberDto {
    @Schema(title = "文章数量")
    private Long article;

    @Schema(title = "分类数量")
    private Long category;

    @Schema(title = "标签数量")
    private Long tag;

    @Schema(title = "说说数量")
    private Long talk;
}
