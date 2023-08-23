package com.wbxnl.blog.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 博客评论
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Data
@Accessors(chain = true)
@TableName("t_comment")
//@ApiModel(value = "Comment对象", description = "博客评论")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    // // @ApiModelProperty("评论ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    // // @ApiModelProperty("评论人账号")
    @TableField("user_auth_id")
    private Integer userAuthId;

    // // @ApiModelProperty("评论类型（1文章2说说3写作4友联）")
    @TableField("topic_type")
    private String topicType;

    // // @ApiModelProperty("评论的主题ID（文章ID说说ID...）")
    @TableField("topic_id")
    private Integer topicId;

    // // @ApiModelProperty("评论内容")
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

    // // @ApiModelProperty("坐标所在位置")
    @TableField("location")
    private String location;

    // // @ApiModelProperty("根评论ID")
    @TableField("root_id")
    private Integer rootId;

    @TableField("parent_id")
    private Integer parentId;

    // // @ApiModelProperty("评论类型(1登录评论2游客评论3匿名评论)")
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

    // // @ApiModelProperty("是否隐藏评论")
    @TableField("hidden")
    private Integer hidden;

    // // @ApiModelProperty("审核情况（0未知1通过-1未通过）")
    @TableField("review")
    private Integer review;

    ////@ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    ////@ApiModelProperty("修改时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    ////@ApiModelProperty("是否已删除")
    @TableField("deleted")
    @TableLogic
    private Integer deleted;


}
