package com.wbxnl.blog.model.vo.params;

import com.wbxnl.blog.model.vo.params.base.QueryParams;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author xiaowansheng
 * @Date 2023/8/13 3:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TalkParams implements QueryParams {
    @Parameter(description = "说说ID")
    private Integer id;

    @Parameter(description = "说说内容")
    private String content;

    @Parameter(description = "状态（1显示2私密）")
    private String status;

    @Parameter(description = "置顶（1置顶0不置顶）")
    private Integer top;

    @Parameter(description = "ip所在地")
    private String ipSource;

    @Parameter(description = "使用设备")
    private String device;

    @Parameter(description = "使用浏览器")
    private String browser;

    @Parameter(description = "定位")
    private String location;

    @Parameter(description = "时间范围：开始时间")
    private Date beginDate;

    @Parameter(description = "时间范围：结束时间")
    private Date endDate;
}
