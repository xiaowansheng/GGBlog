package com.wbxnl.blog.infrastructure.persistent.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * <p>
 * 留言簿
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Data
@Accessors(chain = true)
@TableName("t_guestbook")
public class Guestbook implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("leave_word_key")
    private String leaveWordKey;

    @TableField("username")
    private String username;

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
