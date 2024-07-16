package com.wbxnl.blog.infrastructure.persistent.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * <p>
 * 友情链接
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Data
@Accessors(chain = true)
@TableName("t_friend")
//@ApiModel(value = "Blogroll对象", description = "友情链接")
public class Friend implements Serializable {

    private static final long serialVersionUID = 1L;

    // // @ApiModelProperty("友情链接ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户编号
     */
    @TableField("userAuthId")
    private Integer userAuthId;

    // // @ApiModelProperty("网站名称")
    @TableField("name")
    private String name;

    // // @ApiModelProperty("网站图标链接")
    @TableField("icon")
    private String icon;

    // // @ApiModelProperty("网站地址")
    @TableField("url")
    private String url;

    // // @ApiModelProperty("网站作者")
    @TableField("author")
    private String author;

    // // @ApiModelProperty("网站介绍")
    @TableField("introduction")
    private String introduction;

    // // @ApiModelProperty("审核状态（0待审核1通过-1未通过）")
    @TableField("review")
    private Integer review;

    @TableField("hidden")
    private Integer hidden;


    ////@ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    ////@ApiModelProperty("修改时间")
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    ////@ApiModelProperty("是否已删除")
    @TableField("deleted")
    @TableLogic
    private Integer deleted;


}
