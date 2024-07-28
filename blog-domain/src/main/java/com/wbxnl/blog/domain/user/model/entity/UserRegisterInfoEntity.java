package com.wbxnl.blog.domain.user.model.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/29 0:05
 */
@Data
public class UserRegisterInfoEntity {

    private String userInfoKey;

    private String email;

    private String qq;

    private String nickname;

    private String avatar;

    private String signature;

    private String website;

    private String introduction;
}
