package com.wbxnl.blog.domain.user.model.entity;

import lombok.Data;

import java.util.Date;

/**
 * @description:
 * @author: xiaowansheng
 * @date: 2024/7/16 17:55
 */
@Data
public class UserInfoEntity {

    private Integer id;

    private String email;

    private String qq;

    private String nickname;

    private String avatar;

    private String signature;

    private String website;

    private String introduction;

    private Date createTime;

    private Date updateTime;

}
