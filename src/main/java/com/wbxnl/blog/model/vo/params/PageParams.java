package com.wbxnl.blog.model.vo.params;

import com.wbxnl.blog.model.vo.params.base.QueryParams;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author xiaowansheng
 * @Date 2023/8/8 18:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageParams  implements QueryParams {

    @Parameter(description = "当前页码，从1开始")
    @NotNull(message = "页数不能为空")
    @Min(value = 1,message = "最小不能小于1")
    private Long page = 1L;

    @Parameter(description = "每页显示记录数")
    @NotNull(message = "记录数不能为空")
    @Min(value = 1,message = "最小不能小于1")
    private Long limit = 10L;

    @Parameter(description = "排序字段")
    private String orderField;

    @Parameter(description = "排序方式，可选值(asc、desc)")
    private String order;

}
