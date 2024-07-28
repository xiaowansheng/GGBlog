package com.wbxnl.blog.domain.user.model.entity;

import lombok.Data;

import java.time.LocalDateTime;


/**
 * @description:
 * @author: xiaowansheng
 * @date: 2024/7/16 16:42
 */
@Data
public class UserAuthEntity {
    private Integer id;

    private Integer userInfoKey;

    private String username;

    private String password;

    private String loginType;

    private String thirdPartyId;

    private String thirdPartyProfile;

    private Integer disable;

    private String ipAddressSignup;

    private String ipSourceSignup;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
