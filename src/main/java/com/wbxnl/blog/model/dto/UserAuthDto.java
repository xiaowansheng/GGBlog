package com.wbxnl.blog.model.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
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
@Schema(name = "UserAuthDto",title = "UserAuthDto",description = "响应的用户账号和个人的信息")
public class UserAuthDto implements Serializable {

    private static final long serialVersionUID = 1L;

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


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 修改时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 是否已删除
     */
    private Integer deleted;

    /**
     * （额外）
     */
    private UserInfoDto userInfoDto;

    /**
     * (额外)
     */
    private List<String> roleNames;
}
