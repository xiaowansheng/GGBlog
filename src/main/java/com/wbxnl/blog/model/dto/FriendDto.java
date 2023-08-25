package com.wbxnl.blog.model.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.experimental.Accessors;
//import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 友情链接
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
@Schema(name = "FriendDto",title = "FriendDto",description = "响应的友链信息")
public class FriendDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 友情链接ID
     */
    private Integer id;

    /**
     * 用户编号
     */
    private Integer userAuthId;

    /**
     * 网站名称
     */
    private String name;

    /**
     * 网站图标链接
     */
    private String icon;

    /**
     * 网站地址
     */
    private String url;

    /**
     * 网站作者
     */
    private String author;

    /**
     * 网站介绍
     */
    private String introduction;

    /**
     * 审核状态（0待审核1通过-1未通过）
     */
    private Integer review;

    /**
     * 是否隐藏
     */
    private Integer hidden;

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
}
