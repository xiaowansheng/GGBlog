package com.wbxnl.blog.infrastructure.persistent.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * <p>
 * 博客评论
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Data
@Accessors(chain = true)
@TableName("t_comment")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("username")
    private String username;

    @TableField("topic_type")
    private String topicType;

    @TableField("topic_key")
    private String topicKey;

    @TableField("content")
    private String content;

    @TableField("images")
    private String images;

    @TableField("ip_address")
    private String ipAddress;

    @TableField("ip_source")
    private String ipSource;

    @TableField("device")
    private String device;

    @TableField("browser")
    private String browser;

    @TableField("point")
    private byte[] point;

    @TableField("location")
    private String location;

    @TableField("root_id")
    private Integer rootId;

    @TableField("parent_id")
    private Integer parentId;

    @TableField("type")
    private String type;

    @TableField("nickname")
    private String nickname;

    @TableField("email")
    private String email;

    @TableField("qq")
    private String qq;

    @TableField("hidden")
    private Integer hidden;

    @TableField("review")
    private Integer review;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField("deleted")
    @TableLogic
    private Integer deleted;


}
