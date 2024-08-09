package com.wbxnl.blog.infrastructure.persistent.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * <p>
 * 资源菜单
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Data
@Accessors(chain = true)
@TableName("system_resource")
public class SystemResource implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("resource_key")
    private String resourceKey;

    @TableField("name")
    private String name;

    @TableField("request_method")
    private String requestMethod;

    @TableField("path")
    private String path;

    @TableField("open")
    private Integer open;

    @TableField("parent_key")
    private String parentKey;

    @TableField("perms")
    private String perms;

    @TableField("description")
    private String description;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField("deleted")
    @TableLogic
    private Integer deleted;
}
