package com.wbxnl.blog.model.vo.params;

import com.wbxnl.blog.model.vo.params.base.QueryParams;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author xiaowansheng
 * @Date 2023/8/13 17:14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PictureParams implements QueryParams {
    @Parameter(description = "图片ID")
    private Integer id;

    @Parameter(description = "图片上传人账号ID")
    private Integer userAuthId;

    @Parameter(description = "相册ID")
    private Integer albumId;

    @Parameter(description = "图片名称")
    private String name;

    @Parameter(description = "图片描述")
    private String description;

    @Parameter(description = "图片来源（0未知1原创2二创3转载）")
    private String source;

    @Parameter(description = "状态信息")
    private String status;

    @Parameter(description = "时间区间：开始时间")
    private Date beginDate;

    @Parameter(description = "时间区间：结束时间")
    private Date endDate;
}
