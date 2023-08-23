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
 * 访客信息
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Data
@Accessors(chain = true)
@TableName("t_visitor")
//@ApiModel(value = "Visitor对象", description = "访客信息")
public class Visitor implements Serializable {

    private static final long serialVersionUID = 1L;

    // @ApiModelProperty("访客信息ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    // @ApiModelProperty("标识唯一的访客")
    @TableField("uuid")
    private String uuid;

    // @ApiModelProperty("访问页面类型")
    @TableField("view_type")
    private String viewType;

    // @ApiModelProperty("访问内容id")
    @TableField("view_id")
    private Integer viewId;

    // @ApiModelProperty("ip地址")
    @TableField("ip_address")
    private String ipAddress;

    // @ApiModelProperty("ip所在地")
    @TableField("ip_source")
    private String ipSource;

    // @ApiModelProperty("设备名称")
    @TableField("device")
    private String device;

    // @ApiModelProperty("浏览器名称")
    @TableField("browser")
    private String browser;

    // @ApiModelProperty("坐标")
    @TableField("point")
    private byte[] point;

    // @ApiModelProperty("位置")
    @TableField("location")
    private String location;

    ////@ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    
    ////@ApiModelProperty("创建时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    ////@ApiModelProperty("是否已删除")
    @TableField("deleted")
    @TableLogic
    private Integer deleted;
}
