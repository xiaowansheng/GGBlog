package com.wbxnl.blog.model.vo.params;

import com.wbxnl.blog.model.vo.params.base.QueryParams;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author xiaowansheng
 * @Date 2023/8/12 2:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryParams implements QueryParams {
    @Parameter(description = "编号")
    private Integer id;

    @Parameter(description = "分类名称")
    private String name;

    @Parameter(description = "是否隐藏")
    private Integer hidden;
}
