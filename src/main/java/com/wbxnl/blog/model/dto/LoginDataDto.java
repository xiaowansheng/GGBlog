package com.wbxnl.blog.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @Author xiaowansheng
 * @Date 2023/8/8 16:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Schema(name = "LoginDataDto",title = "LoginDataDto",description = "响应的登录数据信息")
public class LoginDataDto {
    /**
     * 用户名
     */
    private String username;
    /**
     * token
     */
    private String accessToken;

    /**
     * token过期时间
     */
    private Long expire;

    /**
     * 刷新token
     */
    private String refreshToken;
    /**
     * 用户角色集合
     */
    private List<String> roleNames;
}