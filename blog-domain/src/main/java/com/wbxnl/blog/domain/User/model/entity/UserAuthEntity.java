package com.wbxnl.blog.domain.User.model.entity;

import lombok.Data;

import java.util.Date;

/**
 * @description:
 * @author: xiaowansheng
 * @date: 2024/7/16 16:42
 */
@Data
public class UserAuthEntity {
    private Integer id;

    private Integer userInfoId;

    private String username;

    private String password;

    private String loginType;

    private String thirdPartyId;

    private String thirdPartyProfile;

    private Integer disable;

    private String ipAddressSignup;

    private String ipSourceSignup;

    private Date createTime;

    private Date updateTime;

}
