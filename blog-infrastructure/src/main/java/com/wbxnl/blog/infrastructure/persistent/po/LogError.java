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
 * 系统异常错误日志
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
@TableName("log_error")
public class LogError implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("username")
    private String username;

    @TableField("version")
    private String version;

    @TableField("request_url")
    private String requestUrl;

    @TableField("request_method")
    private String requestMethod;

    @TableField("request_param")
    private String requestParam;

    @TableField("module")
    private String module;

    @TableField("calling_method")
    private String callingMethod;

    @TableField("error_name")
    private String errorName;

    @TableField("error_message")
    private String errorMessage;

    @TableField("ip_address")
    private String ipAddress;

    @TableField("ip_source")
    private String ipSource;

    @TableField("device")
    private String device;

    @TableField("browser")
    private String browser;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField("deleted")
    @TableLogic
    private Integer deleted;

}
