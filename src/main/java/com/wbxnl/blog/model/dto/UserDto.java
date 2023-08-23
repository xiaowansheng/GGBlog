package com.wbxnl.blog.model.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author wansheng
 * @createDate 2022/8/27 15:35
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto implements  Serializable{

    private static final long serialVersionUID = 1L;
    /**
     * 用户账号ID
     */
    private Integer userAuthId;

    /**
     * 用户信息ID
     */
    private Integer userInfoId;

    /**
     * 登录用户名（一般是用户信息的邮箱）
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 登录方式（如邮箱、QQ）
     */
    private String loginType;

    /**
     * 权限信息集合
     */
    private List<RoleDto> roles;

    /**
     * （之后可能的扩展）权限信息集合
     */
    private List<String> permissions;

    /**
     * 登录设备数
     */
    private Integer count;
}
