package com.wbxnl.blog.infrastructure.persistent.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * <p>
 * 用户角色
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Data
@Accessors(chain = true)
@TableName("t_role")
//@ApiModel(value = "Role对象", description = "用户角色")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    // // @ApiModelProperty("用户角色ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    // // @ApiModelProperty("角色名称")
    @TableField("name")
    private String name;

    // // @ApiModelProperty("角色标签")
    @TableField("label")
    private String label;

    // // @ApiModelProperty("角色详情介绍")
    @TableField("description")
    private String description;

    // // @ApiModelProperty("是否禁用")
    @TableField("disable")
    private Integer disable;

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
