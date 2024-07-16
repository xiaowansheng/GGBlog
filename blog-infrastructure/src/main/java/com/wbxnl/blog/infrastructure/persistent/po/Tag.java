package com.wbxnl.blog.infrastructure.persistent.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * <p>
 * 文章标签
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Data
@Accessors(chain = true)
@TableName("t_tag")
//@ApiModel(value = "Tag对象", description = "文章标签")
public class Tag implements Serializable {

    private static final long serialVersionUID = 1L;

    // // @ApiModelProperty("文章标签ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    // // @ApiModelProperty("标签名称")
    @TableField("name")
    private String name;

    @TableField("description")
    private String description;

    // // @ApiModelProperty("是否隐藏该标签")
    @TableField("hidden")
    private Integer hidden;

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
