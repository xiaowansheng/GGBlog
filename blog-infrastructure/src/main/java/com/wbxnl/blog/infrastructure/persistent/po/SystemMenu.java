package com.wbxnl.blog.infrastructure.persistent.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * <p>
 * 菜单目录
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Data
@Accessors(chain = true)
@TableName("system_menu")
public class SystemMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("menu_key")
    private String menuKey;

//    @TableField("name")
//    private String name;

    @TableField("title")
    private String title;

    @TableField("icon")
    private String icon;

    @TableField("redirect")
    private String redirect;

    @TableField("path")
    private String path;

    @TableField("component")
    private String component;

    @TableField("hidden")
    private Integer hidden;

    @TableField("sort")
    private Byte sort;

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
