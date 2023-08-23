package com.wbxnl.blog.model.vo.params;

import com.wbxnl.blog.model.vo.UserInfoVo;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author xiaowansheng
 * @Date 2023/8/8 18:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthParams implements QueryParams {

    @Parameter(description = "用户账号ID")
    private Integer id;

    @Parameter(description = "登录用户名（一般是用户信息的邮箱）")
    private String username;

    @Parameter(description = "登录方式（如邮箱、QQ）")
    private String loginType;

    @Parameter(description = "用户是否禁用")
    private Integer disable;

    @Parameter(description = "角色类型")
    private Integer roleId;

    @Parameter(description = "用户信息")
    private String nickname;
}
