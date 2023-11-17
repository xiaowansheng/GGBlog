package com.wbxnl.blog.model.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
//import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.ToString;
import lombok.experimental.Accessors;
//import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 博客评论
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
@Schema(name = "CommentDto",title = "CommentDto",description = "响应的用户评论信息")
public class CommentDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 评论ID
     */
    private Integer id;

    /**
     * 评论人账号
     */
    private Integer userAuthId;

    /**
     * (额外)用户昵称
     */
    private String userName;

    /**
     * 评论类型（1文章2说说3写作4友联）
     */
    private String topicType;

    /**
     * 评论的主题ID（文章ID说说ID...）
     */
    private Integer topicId;

//    /**
//     * (额外)主题名
//     */
//    private String topicName;


    /**
     * (额外)回复人的昵称
     */
    private String replyUserName;
    /**
     * (额外)自己的昵称
     */
    private String infoName;
    /**
     * (额外)自己的头像
     */
    private String infoAvatar;
    /**
     * (额外)自己的邮箱
     */
    private String infoEmail;
    /**
     * (额外)自己的网站
     */
    private String infoWebsite;
    /**
     * (额外)个人介绍
     */
    private String introduction;

    /**
     * (额外)自己的角色ID
     */
    private Integer roleId;
    /**
     * (额外)自己的角色标签
     */
    private String roleLabel;

    /**
     * 评论内容
     */
    private String content;


    private String images;

    /**
     * ip地址
     */
    private String ipAddress;

    /**
     * ip所在地
     */
    private String ipSource;
    /**
     * 使用设备
     */
    private String device;
    /**
     * 使用浏览器
     */
    private String browser;

    // // @ApiModelProperty("坐标")
    private byte[] point;

    // // @ApiModelProperty("坐标所在位置")
    private String location;

    /**
     * 根评论id
     */
    private Integer rootId;

    /**
     * 回复的评论
     */
    private Integer parentId;

    /**
     * 评论类型(1登录评论2游客评论3匿名评论)
     */
    private String type;

    /**
     * 游客别名
     */
    private String nickname;

    /**
     * 游客邮箱
     */
    private String email;

    /**
     * 游客QQ号
     */
    private String qq;

    /**
     * 该条评论以下的其它评论
     */
    private List<CommentDto> children;

    /**
     * 是否隐藏评论
     */
    private Integer hidden;

    /**
     * 是否审核评论再显示
     */
    private Integer review;


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


//    /**
//     * 是否删除
//     */
//    private Integer deleted;
}
