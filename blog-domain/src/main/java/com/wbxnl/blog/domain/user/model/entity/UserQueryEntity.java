package com.wbxnl.blog.domain.user.model.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/16 23:02
 */
@Data
public class UserQueryEntity {

    private Integer id;

    private Integer userInfoKey;

    private String username;

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

    private LocalDateTime beginCreateTime;

    private LocalDateTime endCreateTime;

    private LocalDateTime beginUpdateTime;

    private LocalDateTime endUpdateTime;

}
