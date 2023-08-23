package com.wbxnl.blog.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
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
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Schema(name = "RoleMenuVo",title = "角色菜单关联",description = "新增角色的菜单关联时提交的数据")
public class RoleMenuVo implements Serializable {

    private static final long serialVersionUID = 1L;

//    @Schema(title = "角色菜单关联ID")
//    private Integer id;

    @Schema(title = "角色ID")
    private Integer roleId;

    @Schema(title = "系统菜单ID")
    private Integer menuId;
}
