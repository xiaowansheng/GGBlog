package com.wbxnl.blog.model.vo.params;

import com.wbxnl.blog.model.vo.params.base.QueryParams;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Parameter(description = "注册地")
    private String ipSource;

    @Parameter(description = "设备")
    private String device;

    @Parameter(description = "浏览器")
    private String browser;

    @Parameter(description = "角色类型")
    private Integer roleId;

    @Parameter(description = "昵称")
    private String nickname;

    @Parameter(description = "邮箱")
    private String email;

    @Parameter(description = "QQ")
    private String qq;

    @Parameter(description = "个人网站")
    private String website;
}
