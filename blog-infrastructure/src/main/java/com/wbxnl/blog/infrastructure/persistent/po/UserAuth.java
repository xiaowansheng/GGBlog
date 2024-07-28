package com.wbxnl.blog.infrastructure.persistent.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * <p>
 * 用户账号
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_user_auth")
public class UserAuth implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("user_info_key")
    private String userInfoKey;

    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    @TableField("login_type")
    private String loginType;

    @TableField("third_party_id")
    private String thirdPartyId;

    @TableField("third_party_profile")
    private String thirdPartyProfile;

    @TableField("disable")
    private Integer disable;

    @TableField("ip_address_signup")
    private String ipAddressSignup;

    @TableField("ip_source_signup")
    private String ipSourceSignup;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField("deleted")
    @TableLogic
    private Integer deleted;
}
