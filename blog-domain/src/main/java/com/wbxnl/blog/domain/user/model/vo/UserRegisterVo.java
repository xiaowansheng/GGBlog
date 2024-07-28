package com.wbxnl.blog.domain.user.model.vo;

import lombok.Data;

/**
 * @description:
 * @author: xiaowansheng
 * @date: 2024/7/16 16:42
 */
@Data
public class UserRegisterVo {
    /**
     * 默认为邮箱
     */
    private String username;

    private String password;

    private String verificationCode;

}
