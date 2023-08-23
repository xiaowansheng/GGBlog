package com.wbxnl.blog.model.vo.params;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author xiaowansheng
 * @Date 2023/8/13 18:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentParams implements QueryParams{
    @Parameter(description = "评论ID")
    private Integer id;

    @Parameter(description = "评论人账号")
    private Integer userAuthId;

    @Parameter(description = "评论类型（1文章2说说3写作4友联）")
    private String topicType;

    @Parameter(description = "评论的主题ID（文章ID说说ID...）")
    private Integer topicId;

    @Parameter(description = "评论内容")
    private String content;

    @Parameter(description = "使用的设备")
    private String device;

    @Parameter(description = "使用的浏览器")
    private String browser;

    @Parameter(description = "所在位置")
    private String location;

    @Parameter(description = "根评论ID")
    private Integer rootId;

    @Parameter(description = "评论类型(1登录评论2游客评论3匿名评论)")
    private String type;

    @Parameter(description = "昵称")
    private String nickname;

    @Parameter(description = "游客邮箱")
    private String email;

    @Parameter(description = "游客QQ号")
    private String qq;

    @Parameter(description = "是否隐藏评论")
    private Integer hidden;

    @Parameter(description = "审核状态")
    private Integer review;

    @Parameter(description = "时间区间：开始时间")
    private Date beginDate;

    @Parameter(description = "时间区间：结束时间")
    private Date endDate;

}
