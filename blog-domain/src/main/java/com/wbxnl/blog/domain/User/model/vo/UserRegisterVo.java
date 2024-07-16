package com.wbxnl.blog.domain.User.model.vo;

import lombok.Data;

/**
 * @description:
 * @author: xiaowansheng
 * @date: 2024/7/16 16:42
 */
@Data
public class UserRegisterVo {

    private String email;

    private String password;

    private String verificationCode;

}
