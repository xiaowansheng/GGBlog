package com.wbxnl.blog.domain.user.model.entity;

import lombok.Data;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/28 0:52
 */
@Data
public class UpdatePasswordEntity {

    private String username;

    private String email;

    private String verificationCode;

    private String newPassword;
}
