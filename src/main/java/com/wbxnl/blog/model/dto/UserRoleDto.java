package com.wbxnl.blog.model.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.List;

//import io.swagger.annotations.ApiModel;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.ToString;
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
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Schema(name = "UserRoleDto",title = "UserRoleDto",description = "响应的用户角色的信息")
public class UserRoleDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户账号角色ID
     */
    private Integer id;

    /**
     * 用户账号ID
     */
    private Integer userAuthId;

    /**
     * 角色集合
     */
    private List<RoleDto> rolesList;
}
