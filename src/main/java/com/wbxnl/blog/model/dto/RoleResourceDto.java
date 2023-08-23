package com.wbxnl.blog.model.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.List;

//import io.swagger.annotations.ApiModel;
import lombok.*;
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
//@ApiModel(value = "RoleResourceDto对象", description = "角色资源")
public class RoleResourceDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色资源ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 角色ID
     */
    @TableField("role_id")
    private Integer roleId;

//    /**
//     * 资源ID
//     */
//    @TableField("resource_id")
//    private Integer resourceId;
    /**
     * 角色可用资源集合
     */
    private List<SystemResourceDto> resources;
}
