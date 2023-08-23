package com.wbxnl.blog.model.vo.params;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author xiaowansheng
 * @Date 2023/8/14 1:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorLogParams implements QueryParams{
    @Parameter(description = "错误日志编号")
    private Integer id;

    @Parameter(description = "操作用户ID")
    private Integer userAuthId;

//    @Parameter(description = "操作用户名")
//    private String userName;

    @Parameter(description = "系统版本")
    private String version;

    @Parameter(description = "请求的地址")
    private String requestUrl;

    @Parameter(description = "请求方法")
    private String requestMethod;

    @Parameter(description = "请求参数")
    private String requestParam;

    @Parameter(description = "请求模块")
    private String module;

    @Parameter(description = "调用方法")
    private String callingMethod;

    @Parameter(description = "异常名称")
    private String errorName;

    @Parameter(description = "异常信息")
    private String errorMessage;

//    @Parameter(description = "位置")
//    private String location;

    @Parameter(description = "设备名称")
    private String device;

    @Parameter(description = "浏览器类型")
    private String browser;

    @Parameter(description = "时间间隔：开始时间")
    private Date beginDate;

    @Parameter(description = "时间间隔：结束时间")
    private Date endDate;
}
