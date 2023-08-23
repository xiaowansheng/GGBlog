package com.wbxnl.blog.model.vo;

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
 * 角色资源
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
@Schema(name = "RoleResourceVo",title = "角色资源关联",description = "新增角色的资源信息时提交的数据")
public class RoleResourceVo implements Serializable {

    private static final long serialVersionUID = 1L;

//    @Schema(title = "角色资源关联ID")
//    private Integer id;

    @Schema(title = "角色ID")
    private Integer roleId;

    @Schema(title = "资源ID")
    private Integer resourceId;
}
