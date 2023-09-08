package com.wbxnl.blog.model.vo.params;

import com.wbxnl.blog.model.vo.params.base.QueryParams;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author xiaowansheng
 * @Date 2023/8/13 13:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumParams implements QueryParams {
    @Parameter(description = "相册编号")
    private Integer id;

    @Parameter(description = "相册创建人账号ID")
    private Integer userAuthId;

    @Parameter(description = "相册名称")
    private String name;

    @Parameter(description = "相册描述")
    private String description;

    @Parameter(description = "相册状态（1公开2私密）")
    private String status;

    @Parameter(description = "时间间隔：开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date beginDate;

    @Parameter(description = "时间间隔：结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endDate;
}
