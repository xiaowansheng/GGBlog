package com.wbxnl.blog.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色资源
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Data
@Accessors(chain = true)
@TableName("t_role_resource")
//@ApiModel(value = "RoleResource对象", description = "角色资源")
public class RoleResource implements Serializable {

    private static final long serialVersionUID = 1L;

    ////@ApiModelProperty("角色资源ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    ////@ApiModelProperty("角色ID")
    @TableField("role_id")
    private Integer roleId;

    ////@ApiModelProperty("资源ID")
    @TableField("resource_id")
    private Integer resourceId;


}
