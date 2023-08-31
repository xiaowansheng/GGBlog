package com.wbxnl.blog.model.vo.params;

import com.wbxnl.blog.model.vo.params.base.QueryParams;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author xiaowansheng
 * @Date 2023/8/13 21:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendParams implements QueryParams {

    // // @ApiModelProperty("友情链接ID")
    private Integer id;
    /**
     * 用户编号
     */
    private Integer userAuthId;

    // // @ApiModelProperty("网站名称")
    private String name;

    // // @ApiModelProperty("网站作者")
    private String author;

    // // @ApiModelProperty("网站介绍")
    private String introduction;

    // // @ApiModelProperty("审核状态（0待审核1通过-1未通过）")
    private Integer review;

    // // @ApiModelProperty("是否隐藏")
    private Integer hidden;
}
