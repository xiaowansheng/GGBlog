package com.wbxnl.blog.domain.user.model.entity;

import lombok.Data;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/29 0:01
 */
@Data
public class UserRegisterDataEntity {

    /**
     * 默认为邮箱
     */
    private String username;

    private String userInfoKey;

    private String email;

    private String password;

    private String ipAddressSignup;

    private String ipSourceSignup;

}
