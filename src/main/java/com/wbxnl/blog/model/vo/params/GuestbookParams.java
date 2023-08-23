package com.wbxnl.blog.model.vo.params;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author xiaowansheng
 * @Date 2023/8/14 0:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuestbookParams implements QueryParams{
    @Parameter(description = "留言编号")
    private Integer id;

    @Parameter(description = "留言账号ID")
    private Integer userAuthId;

    @Parameter(description = "留言内容")
    private String content;

    @Parameter(description = "使用设备")
    private String device;

    @Parameter(description = "使用浏览器")
    private String browser;

    @Parameter(description = "位置")
    private String location;

    @Parameter(description = "留言类型(1登录留言2游客留言3匿名留言)")
    private String type;

    @Parameter(description = "游客别名")
    private String nickname;

    @Parameter(description = "游客邮箱")
    private String email;

    @Parameter(description = "游客QQ号")
    private String qq;

    @Parameter(description = "是否隐藏留言")
    private Integer hidden;

    @Parameter(description = "审核状态（0未审核）")
    private Integer review;

    @Parameter(description = "时间范围：开始时间")
    private Date beginDate;

    @Parameter(description = "时间范围：结束时间")
    private Date endDate;
}
