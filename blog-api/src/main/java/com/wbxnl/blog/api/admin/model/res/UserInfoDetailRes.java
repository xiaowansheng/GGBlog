package com.wbxnl.blog.api.admin.model.res;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/8/4 17:09
 */
@Data
public class UserInfoDetailRes {

    private Integer id;

    private Integer userInfoKey;

    private String username;

    private String password;

//    private String loginType;
//
//    private String thirdPartyId;
//
//    private String thirdPartyProfile;

    private Integer disable;

    private String ipAddressSignup;

    private String ipSourceSignup;

    private String email;

    private String qq;

    private String nickname;

    private String avatar;

    private String signature;

    private String website;

    private String introduction;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
