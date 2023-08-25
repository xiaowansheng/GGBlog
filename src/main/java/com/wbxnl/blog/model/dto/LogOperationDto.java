package com.wbxnl.blog.model.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
//import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
//import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 操作日志
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
@Schema(name = "LogOperationDto",title = "LogOperationDto",description = "响应的操作日志信息")
public class LogOperationDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer userAuthId;

    private String userName;

    private String version;

    private String requestUrl;

    private String module;

    private String callingMethod;

    private String type;

    private String description;

    private String requestMethod;

    private String requestParam;

    private String responseData;

    private Long elapsedTime;

    private String ipAddress;

    private String ipSource;

    private String device;

    private String browser;

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
     * 是否被删除
     */
    private Integer deleted;
}
