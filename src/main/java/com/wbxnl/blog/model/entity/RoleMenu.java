package com.wbxnl.blog.model.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色菜单
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Data
@Accessors(chain = true)
//@ApiModel(value = "RoleMenu对象", description = "角色菜单")
@TableName("t_role_menu")
public class RoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    ////@ApiModelProperty("角色菜单ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    ////@ApiModelProperty("角色ID")
    @TableField("role_id")
    private Integer roleId;

    ////@ApiModelProperty("系统菜单")
    @TableField("menu_id")
    private Integer menuId;


}
