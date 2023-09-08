package com.wbxnl.blog.model.vo.params;

import com.wbxnl.blog.model.vo.params.base.QueryParams;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 访客查询参数
 *
 * @Author xiaowansheng
 * @Date 2023/8/14 9:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisitorParams implements QueryParams {

    @Parameter(description = "访客编号")
    private Integer id;

    @Parameter(description = "访客唯一标识")
    private String uuid;

    @Parameter(description = "话题类型")
    private String viewType;

    @Parameter(description = "话题编号")
    private Integer viewId;

    @Parameter(description = "位置")
    private String location;

    @Parameter(description = "设备名称")
    private String device;

    @Parameter(description = "浏览器名称")
    private String browser;

    @Parameter(description = "时间间隔：开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date beginDate;

    @Parameter(description = "时间间隔：结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endDate;

}
