package com.wbxnl.blog.domain.user.model.aggregate;

import lombok.Data;

import java.time.LocalDateTime;


/**
 * @description:
 * @author: xiaowansheng
 * @date: 2024/7/16 17:52
 */
@Data
public class UserBaseInfoAggregate {

    private Integer id;

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
}
