package com.wbxnl.blog.model.dto;

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
public class LoginDataDto {
    /**
     * 用户名
     */
    private String username;
    /**
     * token
     */
    private String token;

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