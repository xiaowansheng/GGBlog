package com.wbxnl.blog.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

import lombok.Data;
import lombok.experimental.Accessors;

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
//@ApiModel(value = "UserAuth对象", description = "用户账号")
public class UserAuth implements Serializable {

    private static final long serialVersionUID = 1L;

    // // @ApiModelProperty("用户账号ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    // // @ApiModelProperty("用户信息ID")
    @TableField("user_info_id")
    private Integer userInfoId;

    // // @ApiModelProperty("登录用户名（一般是用户信息的邮箱）")
    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    // // @ApiModelProperty("登录方式（如邮箱、QQ）")
    @TableField("login_type")
    private String loginType;

    // // @ApiModelProperty("第三方登录id")
    @TableField("third_party_id")
    private String thirdPartyId;

    // // @ApiModelProperty("第三方登录信息")
    @TableField("third_party_profile")
    private String thirdPartyProfile;

    // // @ApiModelProperty("用户是否禁用")
    @TableField("disable")
    private Integer disable;

    // // @ApiModelProperty("注册时ip")
    @TableField("ip_address_signup")
    private String ipAddressSignup;

    // // @ApiModelProperty("注册ip所在地")
    @TableField("ip_source_signup")
    private String ipSourceSignup;

    ////@ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    ////@ApiModelProperty("修改时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    ////@ApiModelProperty("是否已删除")
    @TableField("deleted")
    @TableLogic
    private Integer deleted;
}
