package com.wbxnl.blog.model.vo.params;

import com.wbxnl.blog.model.vo.params.base.QueryParams;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author xiaowansheng
 * @Date 2023/8/14 2:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageViewParams implements QueryParams {

    @Parameter(description = "访问量ID")
    private Integer id;

    @Parameter(description = "访问量：最小值")
    private Long min;

    @Parameter(description = "访问量：最大值")
    private Long max;

    @Parameter(description = "访问量类型（1网站访问量2博客文章访问量3说说访问量4写作文章访问量5相册访问量6友链访问量....）")
    private String viewType;

    @Parameter(description = "不同访问类型的对应表ID")
    private Integer viewId;
}
