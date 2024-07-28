package com.wbxnl.blog.infrastructure.persistent.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;


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
public class Visitor implements Serializable {

    private static final long serialVersionUID = 1L;

    // @ApiModelProperty("访客信息ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("uuid")
    private String uuid;

    @TableField("view_type")
    private String viewType;

    @TableField("view_id")
    private Integer viewId;

    @TableField("ip_address")
    private String ipAddress;

    @TableField("ip_source")
    private String ipSource;

    @TableField("device")
    private String device;

    @TableField("browser")
    private String browser;

    @TableField("point")
    private byte[] point;

    @TableField("location")
    private String location;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField("deleted")
    @TableLogic
    private Integer deleted;
}
