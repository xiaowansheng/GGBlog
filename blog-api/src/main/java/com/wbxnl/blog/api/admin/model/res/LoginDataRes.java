package com.wbxnl.blog.api.admin.model.res;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/8/4 16:58
 */
@Data
public class LoginDataRes {

    private Integer userAuthId;

    private String username;

    private String password;

    private String loginType;

    private String email;

    private String qq;

    private String nickname;

    private String avatar;

    private String signature;

    private String website;

    private String introduction;

    private LocalDateTime createTime;

    private String token;

    private String refreshToken;

    private Long tokenExpireTime;
}
