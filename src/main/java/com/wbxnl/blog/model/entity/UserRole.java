package com.wbxnl.blog.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户账号对应角色
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("t_user_role")
//@ApiModel(value = "UserRole对象", description = "用户账号对应角色")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    ////@ApiModelProperty("用户账号角色ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    ////@ApiModelProperty("用户账号ID")
    @TableField("user_auth_id")
    private Integer userAuthId;

    ////@ApiModelProperty("角色ID")
    @TableField("role_id")
    private Integer roleId;


}
