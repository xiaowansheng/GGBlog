package com.wbxnl.blog.domain.user.model.entity;

import lombok.Data;



/**
 * @description:
 * @author: xiaowansheng
 * @date: 2024/7/16 17:55
 */
@Data
public class UserUpdateEntity {

    private String userInfoKey;

    private String email;

    private String qq;

    private String nickname;

    private String avatar;

    private String signature;

    private String website;

    private String introduction;

}
