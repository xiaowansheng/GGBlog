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
 * 说说
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Data
@Accessors(chain = true)
@TableName("t_talk")
//@ApiModel(value = "Talk对象", description = "说说")
public class Talk implements Serializable {

    private static final long serialVersionUID = 1L;

    // // @ApiModelProperty("说说ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    // // @ApiModelProperty("发表人账号ID")
    @TableField("user_auth_id")
    private Integer userAuthId;

    // // @ApiModelProperty("说说内容")
    @TableField("content")
    private String content;

    // // @ApiModelProperty("图片")
    @TableField("images")
    private String images;

    // // @ApiModelProperty("状态（1显示2私密）")
    @TableField("status")
    private String status;

    // // @ApiModelProperty("置顶（1置顶0不置顶）")
    @TableField("top")
    private Integer top;

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
