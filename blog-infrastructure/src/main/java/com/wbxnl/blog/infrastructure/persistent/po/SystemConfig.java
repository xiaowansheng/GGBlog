package com.wbxnl.blog.infrastructure.persistent.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * <p>
 * 网站配置
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Data
@Accessors(chain = true)
@TableName("system_config")
//@ApiModel(value = "SystemWebconfig对象", description = "网站配置")
public class SystemConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    // // @ApiModelProperty("配置ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    // // @ApiModelProperty("配置参数名")
    @TableField("name")
    private String name;

    // // @ApiModelProperty("参数名称")
    @TableField("label")
    private String label;

    // // @ApiModelProperty("配置参数值")
    @TableField("value")
    private String value;

    // // @ApiModelProperty("配置描述信息")
    @TableField("description")
    private String description;

    ////@ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    ////@ApiModelProperty("修改时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    ////@ApiModelProperty("是否已删除")
    @TableField("deleted")
    @TableLogic
    private Integer deleted;


}
