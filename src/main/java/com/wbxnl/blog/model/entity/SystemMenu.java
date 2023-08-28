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
 * 菜单目录
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Data
@Accessors(chain = true)
@TableName("system_menu")
//@ApiModel(value = "SystemMenu对象", description = "菜单目录")
public class SystemMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    // // @ApiModelProperty("菜单名称")
    @TableField("name")
    private String name;

    // // @ApiModelProperty("菜单标题")
    @TableField("title")
    private String title;

    // // @ApiModelProperty("图标")
    @TableField("icon")
    private String icon;

    // // @ApiModelProperty("路由重定向")
    @TableField("redirect")
    private String redirect;

    // // @ApiModelProperty("路由地址")
    @TableField("path")
    private String path;

    // // @ApiModelProperty("组件路径")
    @TableField("component")
    private String component;

    // // @ApiModelProperty("隐藏菜单（0展示1隐藏）")
    @TableField("hidden")
    private Integer hidden;

    // // @ApiModelProperty("排序")
    @TableField("sort")
    private Byte sort;

    // // @ApiModelProperty("父类ID")
    @TableField("parent_id")
    private Integer parentId;

    // // @ApiModelProperty("权限标识")
    @TableField("perms")
    private String perms;

    // // @ApiModelProperty("描述信息")
    @TableField("description")
    private String description;

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
