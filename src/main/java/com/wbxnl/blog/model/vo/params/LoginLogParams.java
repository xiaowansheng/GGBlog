package com.wbxnl.blog.model.vo.params;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.geo.Point;

import java.util.Date;

/**
 * @Author xiaowansheng
 * @Date 2023/8/14 2:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class LoginLogParams implements QueryParams{
    @Parameter(description = "登录日志编号")
    private Integer id;

    @Parameter(description = "登录用户编号")
    private Integer userAuthId;

    @Parameter(description = "登录设备")
    private String device;

    @Parameter(description = "登录的浏览器")
    private String browser;

    @Parameter(description = "位置")
    private String location;

    @Parameter(description = "时间间隔：开始时间")
    private Date beginDate;

    @Parameter(description = "时间间隔：结束时间")
    private Date endDate;
}
