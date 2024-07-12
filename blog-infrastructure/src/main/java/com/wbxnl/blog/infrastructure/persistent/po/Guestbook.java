package com.wbxnl.blog.infrastructure.persistent.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 留言簿
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Data
@Accessors(chain = true)
@TableName("t_guestbook")
//@ApiModel(value = "Guestbook对象", description = "留言簿")
public class Guestbook implements Serializable {

    private static final long serialVersionUID = 1L;

    // // @ApiModelProperty("留言ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    // // @ApiModelProperty("留言账号ID")
    @TableField("user_auth_id")
    private Integer userAuthId;

    // // @ApiModelProperty("留言内容")
    @TableField("content")
    private String content;

    // // @ApiModelProperty("图片")
    @TableField("images")
    private String images;

    // // @ApiModelProperty("ip地址")
    @TableField("ip_address")
    private String ipAddress;

    // // @ApiModelProperty("ip所在地")
    @TableField("ip_source")
    private String ipSource;

    // // @ApiModelProperty("使用设备")
    @TableField("device")
    private String device;

    // // @ApiModelProperty("使用浏览器")
    @TableField("browser")
    private String browser;

    // // @ApiModelProperty("坐标")
    @TableField("point")
    private byte[] point;

    // // @ApiModelProperty("位置")
    @TableField("location")
    private String location;

    // // @ApiModelProperty("留言类型(1登录留言2游客留言3匿名留言)")
    @TableField("type")
    private String type;

    // // @ApiModelProperty("游客别名")
    @TableField("nickname")
    private String nickname;

    // // @ApiModelProperty("游客邮箱")
    @TableField("email")
    private String email;

    // // @ApiModelProperty("游客QQ号")
    @TableField("qq")
    private String qq;

    // // @ApiModelProperty("是否隐藏留言")
    @TableField("hidden")
    private Integer hidden;

    // // @ApiModelProperty("审核状态（0未审核）")
    @TableField("review")
    private Integer review;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    ////@ApiModelProperty("是否已删除")
    @TableField("deleted")
    @TableLogic
    private Integer deleted;


}
